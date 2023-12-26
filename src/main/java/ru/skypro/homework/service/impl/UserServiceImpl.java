package ru.skypro.homework.service.impl;

import antlr.collections.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.mappers.UsersMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@RequiredArgsConstructor
@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final UsersMapper usersMapper;


    @Value("${image.dir.path}")
    private String imagesDir;


    public NewPassword changePassword( NewPassword newPassword, Authentication authentication) {
        NewPassword resultPassword = new NewPassword();
        authentication.getName();
        resultPassword.getCurrentPassword();
        resultPassword.setNewPassword(newPassword.getNewPassword());
        return resultPassword;
    }


        public User getMe(Authentication authentication) {
            List<User> collect = userRepository.findById(authentication).stream().map(e -> usersMapper.usersToDto(e)).collect(Collectors.toList());
        return (User) collect;;
        }


        public UpdateUser updateUser(UpdateUser updateUser, Authentication authentication) {
        List<UpdateUser> collect = userRepository.findById(authentication).stream().map(e -> usersMapper.updateUserToDto(e)).collect(Collectors.toList());
            collect =  userRepository.save(authentication);
            return (UpdateUser) collect;

        }

//        @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//        public String uploadImageToUser () throws IOException {
//            userRepository.save(image);
//            return ResponseEntity.ok().build();
//        }
    public void updateImage(MultipartFile file, Authentication authentication) throws IOException {
    Path filePath = Path.of("./image", authentication + "." + getExtension(file.getOriginalFilename()));
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
    UserEntity userEntity = userRepository.findById(authentication).orElseGet(UserEntity::new);
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


