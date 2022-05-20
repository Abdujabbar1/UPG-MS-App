package uz.pdp.productreviewserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.productreviewserver.entity.ProductReview;
import uz.pdp.productreviewserver.entity.enums.Status;

import java.util.List;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

//    @Query(nativeQuery = true,value = "select b.id,\n" +
//            "       b.book_id,\n" +
//            "       b.user_id,\n" +
//            "       b.review_body,\n" +
//            "       b.rate,\n" +
//            "       b.created_at, \n" +
//            "       b.status\n" +
//            "from book_reviews b\n" +
//            "where book_id = :bookId\n" +
//            "and b.status = 'ACCEPTED'")
    List<ProductReview> getByProductId(Long bookId);

    List<ProductReview> findAllByBookIdAndStatus(Long bookId, Status status);


//    @Query(nativeQuery = true,value = "select\n" +
//            "       avg(b.rate) as rate\n" +
//            "from book_reviews b\n" +
//            "where book_id = :bookId")
    Double getAverage(Long bookId);
}
