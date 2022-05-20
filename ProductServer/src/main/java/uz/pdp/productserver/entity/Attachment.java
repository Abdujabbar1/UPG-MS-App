package uz.pdp.productserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.productserver.entity.absEntity.AbsClass;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Attachment extends AbsClass {

    @Column(nullable = false)
    private String originName;
    @Column(nullable = false)
    private String contentType;
    @Column(nullable = false)
    private long size;
}
