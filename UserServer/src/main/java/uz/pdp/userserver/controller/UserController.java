package uz.pdp.userserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.userserver.common.ApiResponse;
import uz.pdp.userserver.dtos.UserDto;
import uz.pdp.userserver.service.UserService;

@RestController
@RequestMapping("/api/user-service")
@RequiredArgsConstructor
public class UserController {

    UserService userService;

    @PutMapping("/{userId}")
    public HttpEntity<?> editUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.editUser(userId, userDto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }
}
