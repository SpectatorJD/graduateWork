package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.mappers.UsersMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
@RequiredArgsConstructor
@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final UsersMapper usersMapper;

    @Value("${image.dir.path}")
    private String imagesDir;
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

//        @PostMapping("/set_password")
        public NewPassword createPassword( NewPassword password) {
            return userRepository.save(password);
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


//        @GetMapping("/me")
        public User getMe() {

        return userRepository.findAll();
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
//        @PatchMapping("/me")
        public ResponseEntity<UpdateUser> updateUser() {
            return userRepository.save( updateUser);
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
//        @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public String uploadImageToUser () throws IOException {
            userRepository.save(image);
            return ResponseEntity.ok().build();
        }
    public void uploadImage(Long id, MultipartFile file) throws IOException {
    UserEntity user = findOne(id);
    Path filePath = Path.of("./image", id + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
    InputStream is = file.getInputStream();
    OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
    BufferedInputStream bis = new BufferedInputStream(is, 1024);
    BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
        bis.transferTo(bos);
    }
    UserEntity userEntity = userRepository.findById(id).orElseGet(UserEntity::new);
        userEntity.setId(userEntity.getId());
        userEntity.setImage(file.toString());
        userRepository.save(userEntity);
}
    //    file name gets extended
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    public Optional<UserEntity> findOne(long id) {
        return userRepository.findById(id);
    }
    }


