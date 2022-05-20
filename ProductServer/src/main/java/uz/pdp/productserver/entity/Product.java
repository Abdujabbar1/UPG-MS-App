package uz.pdp.productserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.productserver.entity.absEntity.AbsClass;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "products")
public class Product extends AbsClass {

    private String name;
    private String shortDescription;
    private double price;
    private double discount;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Attachment coverImg;
    @ManyToMany
    private List<CharacteristicValue> characteristic;
    @ManyToOne
    private Manufacture manufacture;

}
