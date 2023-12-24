package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/Users")
public class UsersController {
    private final UserServiceImpl userService;
//    POST
///users/set_password
//    Обновление пароля
//Request body
//
//    application/json
//    Example Value
//    Schema
//    {
//        "currentPassword": "stringst",
//            "newPassword": "stringst"
//    }
//    Responses
//    Code	Description	Links
//200
//    OK
//
//401
//    Unauthorized
//
//403
//    Forbidden
@Operation(summary = "Обновление пароля")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden")})

    @PostMapping("/set_password")
public ResponseEntity<NewPassword> createPassword(@RequestBody NewPassword password) {
    userService.createPassword(password);
            return ResponseEntity.ok().build();
        }

///users/me
//    Получение информации об авторизованном пользователе
//    Parameters
//            Cancel
//    No parameters
//
//    Execute
//            Responses
//    Code	Description	Links
//200
//    OK
//
//    Media type
//
//    application/json
//    Controls Accept header.
//            Example Value
//    Schema
//    {
//        "id": 0,
//            "email": "string",
//            "firstName": "string",
//            "lastName": "string",
//            "phone": "string",
//            "role": "USER",
//            "image": "string"
//    }
//401
//    Unauthorized

    @Operation(summary = "Получение информации об авторизованном пользователе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = User.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")})

    @GetMapping("/me")
public ResponseEntity<User> getMe() {
        return ResponseEntity.ok(getMe().getBody());
    }

//            PATCH
///users/me
//    Обновление информации об авторизованном пользователе
//No parameters
//
//    Request body
//
//    application/json
//    {
//        "firstName": "string",
//            "lastName": "string",
//            "phone": "+7(001 74307-16"
//    }
//    Execute
//            Responses
//    Code	Description	Links
//200
//    OK
//
//    Media type
//
//    application/json
//    Controls Accept header.
//            Example Value
//    Schema
//    {
//        "firstName": "string",
//            "lastName": "string",
//            "phone": "+7(177 45478-48"
//    }
//
//401
//    Unauthorized
@Operation(summary = "Обновление информации об авторизованном пользователе")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        array = @ArraySchema(schema = @Schema(implementation = UpdateUser.class)))),
        @ApiResponse(responseCode = "401", description = "Unauthorized")})

@PatchMapping("/me")
public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser) {
    UpdateUser foundUpdateUser = userService.updateUser(updateUser);
    if (foundUpdateUser == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    return ResponseEntity.ok(updateUser);
}

//            PATCH
///users/me/image
//    Обновление аватара авторизованного пользователя
//Parameters
//Cancel
//Reset
//No parameters
//
//Request body
//
//multipart/form-data
//image *
//string($binary)
//Файл не выбран
//Execute
//Responses
//Code	Description	Links
//200
//OK
//
//No links
//401
//Unauthorized
@Operation(summary = "Обновление аватара авторизованного пользователя")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "401", description = "Unauthorized")})

@PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<String> uploadImage (@RequestParam MultipartFile image) throws IOException {
    userService.uploadImage(image);
    return ResponseEntity.ok().build();
}
}
