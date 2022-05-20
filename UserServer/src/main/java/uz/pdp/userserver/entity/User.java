package uz.pdp.userserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.userserver.entity.absEntity.AbsClass;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User extends AbsClass {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    @ManyToMany
    private Set<Role> roles = new HashSet<>();
}
