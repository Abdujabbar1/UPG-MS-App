package uz.pdp.productserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.productserver.entity.Manufacture;

public interface ManufactureRepo extends JpaRepository<Manufacture, Long> {
}
