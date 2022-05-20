package uz.pdp.userserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.userserver.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
}
