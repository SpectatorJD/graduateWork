//package ru.skypro.homework.controller;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import ru.skypro.homework.dto.NewPassword;
//import ru.skypro.homework.dto.UpdateUser;
//import ru.skypro.homework.dto.User;
//import ru.skypro.homework.service.UserService;
//
//import java.io.IOException;
//
//@Slf4j
//@CrossOrigin(value = "http://localhost:3000")
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/Users")
//public class UsersController {
//    private final UserService userService;
//
////@Operation(summary = "Обновление пароля")
////@ApiResponses(value = {
////        @ApiResponse(responseCode = "200", description = "OK"),
////        @ApiResponse(responseCode = "401", description = "Unauthorized"),
////        @ApiResponse(responseCode = "403", description = "Forbidden")})
//
//    @PostMapping("/set_password")
//    public ResponseEntity<NewPassword> setPassword(@RequestBody NewPassword newPassword, Authentication authentication) {
//    NewPassword resultPassword = new NewPassword();
//    userService.changePassword(
//                    authentication.getName(),
//                    newPassword.getCurrentPassword(),
//                    newPassword.getNewPassword()
//            )
//            .ifPresent(resultPassword::setCurrentPassword);
//    return ResponseEntity.ok(resultPassword);
//}
//
////    @Operation(summary = "Получение информации об авторизованном пользователе")
////    @ApiResponses(value = {
////            @ApiResponse(responseCode = "200", description = "Ok",
////                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
////                            array = @ArraySchema(schema = @Schema(implementation = User.class)))),
////            @ApiResponse(responseCode = "401", description = "Unauthorized")})
//
//    @GetMapping("/me")
//public ResponseEntity<User> getMe(Authentication authentication) {
//    User user = userService.getMe(authentication);
//        return ResponseEntity.ok(user);
//    }
//
//
////@Operation(summary = "Обновление информации об авторизованном пользователе")
////@ApiResponses(value = {
////        @ApiResponse(responseCode = "200", description = "OK",
////                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
////                        array = @ArraySchema(schema = @Schema(implementation = UpdateUser.class)))),
////        @ApiResponse(responseCode = "401", description = "Unauthorized")})
//
//@PatchMapping("/me")
//public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser, Authentication authentication) {
//    UpdateUser foundUpdateUser = userService.updateUser(updateUser,authentication);
//    if (foundUpdateUser == null) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
//    return ResponseEntity.ok(foundUpdateUser);
//}
//
//
////@Operation(summary = "Обновление аватара авторизованного пользователя")
////@ApiResponses(value = {
////        @ApiResponse(responseCode = "200", description = "OK"),
////        @ApiResponse(responseCode = "401", description = "Unauthorized")})
//
//@PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//public ResponseEntity<String> updateImage (@RequestParam MultipartFile image, Authentication authentication) throws IOException {
//    String photo = userService.updateImage(image,authentication);
//    return ResponseEntity.ok(photo);
//    }
//}
