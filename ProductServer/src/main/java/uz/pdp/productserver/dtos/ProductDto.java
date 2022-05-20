package uz.pdp.productserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.productserver.entity.Category;
import uz.pdp.productserver.entity.CharacteristicValue;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private String name;
    private String shortDescription;
    private double price;
    private double discount;
    private long categoryId;
    private long coverImgId;
    private List<Long> characteristicId;
    private long manufactureId;
}
