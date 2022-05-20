package uz.pdp.productserver.entity.absEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class AbsClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
