package uz.pdp.clients.bookReview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate
public class ProductReviewDto {
    private long id;
    private long productId;
    private long userId;
    private String reviewBody;
    private Timestamp createdAt;

}
