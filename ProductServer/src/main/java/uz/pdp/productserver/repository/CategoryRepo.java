package uz.pdp.productserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.productserver.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
