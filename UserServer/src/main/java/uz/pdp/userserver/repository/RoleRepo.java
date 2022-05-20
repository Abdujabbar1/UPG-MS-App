package uz.pdp.userserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.userserver.entity.Role;

import java.util.Set;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Set<Role> findByName(String name);
}
