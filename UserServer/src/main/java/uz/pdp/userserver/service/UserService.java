package uz.pdp.userserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.userserver.common.ApiResponse;
import uz.pdp.userserver.dtos.UserDto;
import uz.pdp.userserver.entity.User;
import uz.pdp.userserver.repository.RoleRepo;
import uz.pdp.userserver.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public ApiResponse getAllUsers() {
        List<User> userList = userRepo.findAll();
        if (userList.isEmpty()) {
            return new ApiResponse("Not Found", false);
        }
        return new ApiResponse("Success!", true, userList);
    }

    public ApiResponse getUserById(Long userid){
        Optional<User> userOptional = userRepo.findById(userid);
        if (userOptional.isEmpty()) {
            return new ApiResponse("Not Found", false);
        }
        return new ApiResponse("Success!", true, userOptional.get());
    }

    public ApiResponse addUser(UserDto userDto){
        try{
            User newUser = new User();

            newUser.setFullName(userDto.getFullName());
            newUser.setEmail(userDto.getEmail());
            newUser.setPhoneNumber(userDto.getPhoneNumber());
            newUser.setPassword(userDto.getPassword());
            newUser.setRoles(roleRepo.findByName("USER_ROLE"));

            userRepo.save(newUser);

            return new ApiResponse("Success!", true, newUser);
        } catch (Exception e){
            return new ApiResponse("Error!", false);
        }
    }

    public ApiResponse editUser(Long id, UserDto userDto){
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent()) {
            return new ApiResponse("User not found", false);
        }
        User user = optionalUser.get();

        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        userRepo.save(user);
        return new ApiResponse("Success", true);
    }
}
