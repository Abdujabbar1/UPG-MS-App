package uz.pdp.productserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.productserver.common.ApiResponse;
import uz.pdp.productserver.dtos.ProductDto;
import uz.pdp.productserver.entity.*;
import uz.pdp.productserver.repository.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final AttachmentRepo attachmentRepo;
    private final AttachmentContentRepo contentRepo;
    private final CharacteristicValueRepo characteristicRepo;
    private final CategoryRepo categoryRepo;
    private final ManufactureRepo manufactureRepo;

    public ApiResponse getAllProducts() {
        List<Product> productList = productRepo.findAll();
        if (productList.isEmpty()) {
            return new ApiResponse("Not Found!", false);
        }
        return new ApiResponse("Success!", true, productList);
    }

    public ApiResponse addProduct(ProductDto productDto) {
        try {

            Optional<Attachment> attachmentOptional = attachmentRepo.findById(productDto.getCoverImgId());
            if (attachmentOptional.isEmpty()) {
                return new ApiResponse("Photo Not Found!", false);
            }
            Optional<Category> categoryOptional = categoryRepo.findById(productDto.getCategoryId());
            if (categoryOptional.isEmpty()) {
                return new ApiResponse("Category Not Found!", false);
            }
            Optional<Manufacture> manufactureOptional = manufactureRepo.findById(productDto.getManufactureId());
            if (manufactureOptional.isEmpty()) {
                return new ApiResponse("Manufacture Not Found!", false);
            }
            List<CharacteristicValue> valueList = characteristicRepo.findAllById(productDto.getCharacteristicId());

            Product newProduct = new Product();
            newProduct.setName(productDto.getName());
            newProduct.setShortDescription(productDto.getShortDescription());
            newProduct.setPrice(productDto.getPrice());
            newProduct.setDiscount(productDto.getDiscount());
            newProduct.setCategory(categoryOptional.get());
            newProduct.setCoverImg(attachmentOptional.get());
            newProduct.setManufacture(manufactureOptional.get());
            newProduct.setCharacteristic(valueList);

            productRepo.save(newProduct);

            return new ApiResponse("Success!", true, newProduct);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse editProduct(Long productId, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isEmpty()) {
            return new ApiResponse("Product Not Found!", false);
        }
        try {
            Optional<Attachment> attachmentOptional = attachmentRepo.findById(productDto.getCoverImgId());
            if (attachmentOptional.isEmpty()) {
                return new ApiResponse("Photo Not Found!", false);
            }
            Optional<Category> categoryOptional = categoryRepo.findById(productDto.getCategoryId());
            if (categoryOptional.isEmpty()) {
                return new ApiResponse("Category Not Found!", false);
            }
            Optional<Manufacture> manufactureOptional = manufactureRepo.findById(productDto.getManufactureId());
            if (manufactureOptional.isEmpty()) {
                return new ApiResponse("Manufacture Not Found!", false);
            }
            List<CharacteristicValue> valueList = characteristicRepo.findAllById(productDto.getCharacteristicId());

            Product newProduct = optionalProduct.get();
            newProduct.setName(productDto.getName());
            newProduct.setShortDescription(productDto.getShortDescription());
            newProduct.setPrice(productDto.getPrice());
            newProduct.setDiscount(productDto.getDiscount());
            newProduct.setCategory(categoryOptional.get());
            newProduct.setCoverImg(attachmentOptional.get());
            newProduct.setManufacture(manufactureOptional.get());
            newProduct.setCharacteristic(valueList);

            productRepo.save(newProduct);

            return new ApiResponse("Success!", true, newProduct);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isEmpty()) {
            return new ApiResponse("Product Not Found!", false);
        }
        try {
            productRepo.delete(optionalProduct.get());

            return new ApiResponse("Success!", true);
        } catch (Exception e){
            return new ApiResponse("Error!", false);
        }
    }
}
