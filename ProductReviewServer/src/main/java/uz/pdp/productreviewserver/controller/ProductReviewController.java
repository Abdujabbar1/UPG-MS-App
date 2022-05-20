package uz.pdp.productreviewserver.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clients.bookReview.ProductReviewDto;
import uz.pdp.productreviewserver.common.ApiResponse;
import uz.pdp.productreviewserver.entity.ProductReview;
import uz.pdp.productreviewserver.service.ProductReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/product-review-service")
@RequiredArgsConstructor
public class ProductReviewController {

    private final ProductReviewService productReviewService;


    @GetMapping
    public ResponseEntity<?> getAllBookReview() {
        return productReviewService.getAllProductReview();
    }

    @GetMapping("/{productId}")
    public List<ProductReviewDto> getBookReviewsByBookId(@PathVariable Long productId) {
        return productReviewService.getProductReviewByProductId(productId);
    }

    @PostMapping
    public ResponseEntity<?> saveBookReview(@RequestBody ProductReview bookReview) {
        ApiResponse apiResponse = productReviewService.saveProductReview(bookReview);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse.getObject());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return productReviewService.delete(id);
    }

    @GetMapping("/average-rating/{productId}")
    public ResponseEntity<?> getAverageRate(@PathVariable Long productId) {
        return productReviewService.getAverageRate(productId);
    }

    @GetMapping("/review")
    public ResponseEntity<?> setReviewStatus(@RequestParam(name = "isAccepted") boolean isAccepted,
                                            @RequestParam(name = "reviewId") Long reviewId) {
        ApiResponse apiResponse = productReviewService.setReviewStatus(isAccepted, reviewId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

}
