package uz.pdp.productserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.productserver.common.ApiResponse;
import uz.pdp.productserver.dtos.ProductDto;
import uz.pdp.productserver.service.ProductService;


@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public HttpEntity<?> getAllProducts() {
        ApiResponse apiResponse = productService.getAllProducts();
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }


//    @GetMapping("/view/{categoryId}")
//    public HttpEntity<?> getProductsByCategory(
//            @PathVariable Long categoryId,
//            @RequestParam(defaultValue = "0") Integer page,
//            @RequestParam(defaultValue = "10") Integer size,
//            @RequestParam(defaultValue = "") String search) {
//
//        ApiResponse apiResponse = productService.getProductsByCategory(page, size, categoryId, search);
//
//        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
//    }

//    @GetMapping("/view/product/{productId}")
//    public HttpEntity<?> getProductsById(
//            @PathVariable Long productId) {
//        ApiResponse apiResponse = productService.getProductsById(productId);
//        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
//
//    }

//    @GetMapping("/view/product/full-description/{productId}")
//    public HttpEntity<?> getProductFullDescription(
//            @PathVariable Long productId) {
//        ApiResponse apiResponse = productService.getProductFullDescription(productId);
//        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
//    }
//
//    @GetMapping("/view/product/full-characteristics/{productId}")
//    public HttpEntity<?> getProductFullCharacteristics(
//            @PathVariable Long productId) {
//        ApiResponse apiResponse = productService.getProductFullCharacteristics(productId);
//        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
//    }


    @PostMapping
    public HttpEntity<?> addProduct(@RequestBody ProductDto productDto) {
        ApiResponse apiResponse = productService.addProduct(productDto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateProduct(@RequestBody ProductDto productDto, @PathVariable Long id) {
        ApiResponse apiResponse = productService.editProduct(id, productDto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Long id) {
        ApiResponse apiResponse = productService.deleteProduct(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }

}


