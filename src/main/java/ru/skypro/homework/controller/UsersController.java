package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @PostMapping("/set_password")
    public ResponseEntity<NewPassword> setPassword(@RequestBody NewPassword newPassword, Authentication authentication) {
    NewPassword resultPassword = userService.changePassword(newPassword,authentication);
    return ResponseEntity.ok(resultPassword);
}

    @GetMapping("/me")
public ResponseEntity<User> getMe(Authentication authentication) {
    User user = userService.getMe(authentication);
        return ResponseEntity.ok(user);
    }

@PatchMapping("/me")
public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser, Authentication authentication) {
    UpdateUser foundUpdateUser = userService.updateUser(updateUser,authentication);
    if (foundUpdateUser == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    return ResponseEntity.ok(foundUpdateUser);
}


@PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<String> updateImage (Authentication authentication, @RequestParam MultipartFile image) throws IOException {
     userService.updateImage(authentication, image);
    return ResponseEntity.ok().build();
    }
}
