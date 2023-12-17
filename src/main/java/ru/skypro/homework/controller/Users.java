package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/Users")
public class Users {
//    POST
///users/set_password
//    Обновление пароля
    @PostMapping("/set_password")
public ResponseEntity<NewPassword> createPassword(@RequestBody NewPassword password) {
    usersService.createPassword(password);
            return ResponseEntity.ok().build();
        }


//            GET
///users/me
//    Получение информации об авторизованном пользователе

@GetMapping("/me")
public ResponseEntity<User> getMe() {
        return ResponseEntity.ok(getMe());
    }

//            PATCH
///users/me
//    Обновление информации об авторизованном пользователе
@PatchMapping("/me")
public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser) {
    UpdateUser foundUpdateUser = usersServis.changeStudent(updateUser);
    if (foundUpdateUser == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    return ResponseEntity.ok(updateUser);
}

//            PATCH
///users/me/image
//    Обновление аватара авторизованного пользователя
@PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<String> uplodeImage (@RequestParam MultipartFile image) throws IOException {
    userService.uploadImage(image);
    return ResponseEntity.ok().build();
}
}
