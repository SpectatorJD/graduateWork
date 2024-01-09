package ru.skypro.homework.service;

import antlr.collections.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.ImageRepository;
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
public class UserService {

    private final UserRepository userRepository;
    private final UsersMapper usersMapper;
    private final ImageRepository imageRepository;
    private final PasswordEncoder encoder;


    @Value("${image.dir.path}")
    private String imagesDir;

    //Обновление пароля
    public NewPassword changePassword(NewPassword newPassword, Authentication authentication) {
        UserEntity userEntity = userRepository.findByEmail(authentication.getName()).orElseThrow();
        userEntity.setPassword(encoder.encode(newPassword.getNewPassword()));
        userRepository.save(userEntity);
        return newPassword;
    }

    //Получение информации об авторизованном пользователе
    public User getMe(Authentication authentication) {
        return userRepository.findByEmail(authentication.getName()).map(usersMapper::usersToDto).orElseThrow();
    }

    //Обновление информации об авторизованном пользователе
    public UpdateUser updateUser(UpdateUser updateUser, Authentication authentication) {
        UserEntity userEntity = userRepository.findByEmail(authentication.getName()).orElseThrow();
        userEntity.setFirstName(updateUser.getFirstName());
        userEntity.setLastName(updateUser.getLastName());
        userEntity.setPhone(updateUser.getPhone());
        userRepository.save(userEntity);
        return usersMapper.updateUserToDto(userEntity);

    }

    //Обновление аватара авторизованного пользователя
    public void updateImage(Authentication authentication,MultipartFile file) throws IOException {

        UserEntity users = userRepository.findByEmail(authentication.getName()).get();
        Path filePath = Path.of("./image", authentication.getName() + "." + getExtension(file.getOriginalFilename()));
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
        Image image = imageRepository.findById(users.getId()).orElseGet(Image::new);
        image.setUsers(users);
        image.setFileSize(file.getSize());
        image.setMediaType(file.getContentType());
        image.setData(file.getBytes());
        imageRepository.save(image);
    }

    //    file name gets extended
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}


