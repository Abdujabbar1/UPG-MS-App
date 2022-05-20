package uz.pdp.productserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.productserver.common.ApiResponse;
import uz.pdp.productserver.dtos.CategoryDto;
import uz.pdp.productserver.entity.Attachment;
import uz.pdp.productserver.entity.Category;
import uz.pdp.productserver.repository.CategoryRepo;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepo categoryRepository;

    // Get All Category
    public ApiResponse getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) {
            return new ApiResponse("Category is Empty!", false);
        }
        return new ApiResponse("Success!", true, categoryList);
    }

    // Get ById Category
    public ApiResponse getCategoryById(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        return optionalCategory.map(category -> new ApiResponse("Success!", true, category))
                .orElseGet(() -> new ApiResponse("Category not found!", false));
    }


    // Add New Category
    public ApiResponse addCategory(CategoryDto categoryDto) {
        Category categoryByName = categoryRepository.findByName(categoryDto.getName());
        if (categoryByName != null) {
            return new ApiResponse("A category with this name exists", false);
        }
        try {
            Category newCategory = new Category();

            newCategory.setName(categoryDto.getName());

            Category saveCategory = categoryRepository.save(newCategory);

            return new ApiResponse("Success!", true, saveCategory);

        } catch (Exception e) {
            return new ApiResponse("Error!", false);
        }
    }

    // Edit Old Category
    public ApiResponse editCategory(Long categoryId, CategoryDto categoryDto) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (!categoryOptional.isPresent()) {
            return new ApiResponse("Category not found!", false);
        }
        try {
            Category newCategory = categoryOptional.get();

            newCategory.setName(categoryDto.getName());

            categoryRepository.save(newCategory);

            return new ApiResponse("Success!", true, newCategory);
        } catch (Exception e) {
            return new ApiResponse("Error!", false);
        }
    }

    // Delete Category
    public ApiResponse deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (!categoryOptional.isPresent()) {
            return new ApiResponse("Category not found!", false);
        }

        try {

            Category category = categoryOptional.get();

            categoryRepository.delete(category);

            return new ApiResponse("Success", true);
        } catch (Exception e) {

            return new ApiResponse("Error!", false);
        }
    }
}
