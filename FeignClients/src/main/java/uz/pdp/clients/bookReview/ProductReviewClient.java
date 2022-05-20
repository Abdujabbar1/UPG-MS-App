package uz.pdp.clients.bookReview;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("product-review-service")
public interface ProductReviewClient {
    @GetMapping("/api/product-review-service/{productId}")
    List<ProductReviewDto> getBookReviews(@PathVariable Long productId);

    @GetMapping("/api/product-review-service/average-rating/{productId}")
    Double getBookAverageRating(@PathVariable Long productId);

}
