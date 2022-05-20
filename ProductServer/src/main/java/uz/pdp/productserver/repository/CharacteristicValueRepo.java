package uz.pdp.productserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.productserver.entity.CharacteristicValue;

public interface CharacteristicValueRepo extends JpaRepository<CharacteristicValue, Long> {
}
