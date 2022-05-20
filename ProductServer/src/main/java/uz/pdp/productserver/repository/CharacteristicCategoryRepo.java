package uz.pdp.productserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.productserver.entity.CharacteristicCategory;

public interface CharacteristicCategoryRepo extends JpaRepository<CharacteristicCategory, Long> {
}
