package uz.pdp.productreviewserver.entity;

//Asilbek Fayzullayev 21.04.2022 11:27   

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import uz.pdp.productreviewserver.entity.enums.Status;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "product_reviews")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY
    )
    private long id;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long userId;

    private String reviewBody;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    @OrderBy
    @CreationTimestamp
    @Column(nullable = false, updatable = false, columnDefinition = " timestamp default now() ")
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());


}
