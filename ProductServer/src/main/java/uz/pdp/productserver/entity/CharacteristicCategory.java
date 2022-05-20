package uz.pdp.productserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.productserver.entity.absEntity.AbsClass;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "characteristic_category")
public class CharacteristicCategory extends AbsClass {
    private String name;
}
