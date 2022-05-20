package uz.pdp.productserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.productserver.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
