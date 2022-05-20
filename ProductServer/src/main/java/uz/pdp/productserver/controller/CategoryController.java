package uz.pdp.productserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.productserver.common.ApiResponse;
import uz.pdp.productserver.dtos.CategoryDto;
import uz.pdp.productserver.service.CategoryService;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public HttpEntity<?> allCategories(){
        ApiResponse apiResponse = categoryService.getAllCategories();
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }

    @GetMapping("/{categoryId}")
    public HttpEntity<?> getCategoryById(@PathVariable Long categoryId){
        ApiResponse apiResponse = categoryService.getCategoryById(categoryId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addCategory(@RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }

    @PutMapping("/{categoryId}")
    public HttpEntity<?> editCategory(@PathVariable Long categoryId, @RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.editCategory(categoryId, categoryDto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }

    @DeleteMapping("/{categoryId}")
    public HttpEntity<?> deleteCategory(@PathVariable Long categoryId){
        ApiResponse apiResponse = categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }
}
