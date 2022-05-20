package uz.pdp.userserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
}
