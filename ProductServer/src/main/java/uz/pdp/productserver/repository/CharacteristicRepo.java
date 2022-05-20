package uz.pdp.productserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.productserver.entity.Characteristic;

public interface CharacteristicRepo extends JpaRepository<Characteristic, Long> {
}
