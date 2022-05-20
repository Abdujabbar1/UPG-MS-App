package uz.pdp.productreviewserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.pdp.clients.book.ProductClient;
import uz.pdp.clients.bookReview.ProductReviewDto;
import uz.pdp.productreviewserver.common.ApiResponse;
import uz.pdp.productreviewserver.dto.ReviewEmailDto;
import uz.pdp.productreviewserver.entity.ProductReview;
import uz.pdp.productreviewserver.entity.enums.Status;
import uz.pdp.productreviewserver.repository.ProductReviewRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductReviewService {

    private final ProductReviewRepository productReviewRepo;
    private final RabbitTemplate rabbitTemplate;
    private final RestTemplate restTemplate;
    private final ProductClient productClient;

    @Value("${spring.rabbitmq.exchange}")
    String exchange;
    @Value("${spring.rabbitmq.routingkey}")
    String routingKey;

    public ResponseEntity<?> getAllProductReview() {
        List<ProductReview> all = productReviewRepo.findAll();
        return ResponseEntity.ok(all);
    }

    public List<ProductReviewDto> getProductReviewByProductId(Long productId) {
        List<ProductReview> byProductId = productReviewRepo.findAllByBookIdAndStatus(productId, Status.ACCEPTED);

        List<ProductReviewDto> bookReviewDtoList = new ArrayList<>();
        for (ProductReview bookReview : byProductId) {
            ProductReviewDto bookReviewDto = new ProductReviewDto(bookReview.getId(), bookReview.getProductId(), bookReview.getUserId(),
                    bookReview.getReviewBody(), bookReview.getCreatedAt());
            bookReviewDtoList.add(bookReviewDto);
        }
        return bookReviewDtoList;
    }

    public ApiResponse saveProductReview(ProductReview productReview) {
        productReview.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        productReview.setStatus(Status.NEW);
        productReview.setUserId(productReview.getUserId());
        ProductReview saveProductReview = productReviewRepo.save(productReview);

        Map<String, Object> productMap = productClient
                .getBookInfo(productReview.getProductId());

        ReviewEmailDto reviewEmailDto = new ReviewEmailDto();
        reviewEmailDto.setBookTitle((String) productMap.get("bookTitle"));
        reviewEmailDto.setReviewBody(productReview.getReviewBody());
        reviewEmailDto.setReviewAuthorName("Nodirbek Nurqulov");
        reviewEmailDto.setReceiverEmail("abdulaziz2000go@gmail.com");
        reviewEmailDto.setAcceptUrl("http://localhost:8050/api/product-review-service/review?isAccepted=true&reviewId=" + saveProductReview.getId());
        reviewEmailDto.setRejectUrl("http://localhost:8050/api/product-review-service/review?isAccepted=false&reviewId=" + saveProductReview.getId());
        reviewEmailDto.setSubject("New product review for your Product");
        sendEmailToBookCreator(reviewEmailDto);

        return new ApiResponse("Review successfully saved", true, productReview);
    }

    public void sendEmailToBookCreator(ReviewEmailDto reviewEmailDto){
        rabbitTemplate.convertAndSend(exchange, routingKey, reviewEmailDto);
    }

    public ResponseEntity<?> delete(Long id) {
        try {
            productReviewRepo.deleteById(id);
        } catch (Exception ignored) {
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getAverageRate(Long productId) {
        Double average = productReviewRepo.getAverage(productId);
        return ResponseEntity.ok(average);

    }

    public ApiResponse setReviewStatus(boolean isAccepted, Long reviewId) {
        Optional<ProductReview> optionalProduct = productReviewRepo.findById(reviewId);
        if (optionalProduct.isEmpty()) return new ApiResponse("Not found", false);
        ProductReview productReview = optionalProduct.get();

        if (isAccepted){
            productReview.setStatus(Status.ACCEPTED);
        } else {
            productReview.setStatus(Status.REJECTED);
        }

        productReviewRepo.save(productReview);
        return new ApiResponse(isAccepted ? "Review is Accepted" : "Review is rejected", true);
    }
}
