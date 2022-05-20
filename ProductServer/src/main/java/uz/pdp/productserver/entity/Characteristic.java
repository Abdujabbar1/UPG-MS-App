package uz.pdp.productserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.productserver.entity.absEntity.AbsClass;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Characteristic extends AbsClass {

    private String name;
    @ManyToOne
    private CharacteristicCategory category;
}
