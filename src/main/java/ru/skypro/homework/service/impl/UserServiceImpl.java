//package ru.skypro.homework.service.impl;
//
//import antlr.collections.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import ru.skypro.homework.dto.*;
//import ru.skypro.homework.entity.AdEntity;
//import ru.skypro.homework.entity.Image;
//import ru.skypro.homework.entity.UserEntity;
//import ru.skypro.homework.repository.ImageRepository;
//import ru.skypro.homework.repository.UserRepository;
//import ru.skypro.homework.service.mappers.UsersMapper;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static com.datical.liquibase.ext.init.InitProjectUtil.getExtension;
//import static java.nio.file.StandardOpenOption.CREATE_NEW;
//
//@RequiredArgsConstructor
//@Service
//public class UserServiceImpl {
//
//    private final UserRepository userRepository;
//    private final UsersMapper usersMapper;
//    private final ImageRepository imageRepository;
//    private final UserDetailsService userDetailsService;
//
//
//    @Value("${image.dir.path}")
//    private String imagesDir;
//
//    private String objectAuthentication() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return ((UserDetails) authentication.getPrincipal()).getUsername();
//    }
//
//    //Обновление пароля
//    public NewPassword changePassword( NewPassword newPassword, Authentication authentication) {
//        NewPassword resultPassword = new NewPassword();
//        authentication.getName();
//        resultPassword.setCurrentPassword(newPassword.getCurrentPassword());
//        resultPassword.setNewPassword(newPassword.getNewPassword());
//        return resultPassword;
//    }
//
//    //Получение информации об авторизованном пользователе
//        public User getMe(Authentication authentication) {
//            List<User> collect = userRepository.findById(authentication.).stream().map(e -> usersMapper.usersToDto(e)).collect(Collectors.toList());
//        return (User) collect;;
//    }
//
//    //Обновление информации об авторизованном пользователе
//        public UpdateUser updateUser(UpdateUser updateUser, Authentication authentication) {
//            UserEntity userEntity = userRepository.findById(authentication.).get();
//            userEntity.setFirstName(updateUser.getFirstName());
//            userEntity.setLastName(updateUser.getLastName());
//            userEntity.setPhone(updateUser.getPhone());
//            userRepository.save(userEntity);
//            return  usersMapper.updateUserToDto(userEntity);;
//
//    }
//
//    //Обновление аватара авторизованного пользователя
//    public void updateImage(MultipartFile file, Authentication authentication) throws IOException {
//
//        public void uploadImage (Integer adId, MultipartFile file) throws
//        IOException {
//            UserEntity user = findUser(authentication);
//            Path filePath = Path.of("./image", authentication + "." + getExtension(file.getOriginalFilename()));
//            Files.createDirectories(filePath.getParent());
//            Files.deleteIfExists(filePath);
//            try (
//                    InputStream is = file.getInputStream();
//                    OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
//                    BufferedInputStream bis = new BufferedInputStream(is, 1024);
//                    BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
//            ) {
//                bis.transferTo(bos);
//            }
//            Image image = imageRepository.findByUserName(authentication.).orElseGet(Image::new);
//            image.setUser(user);
//            image.setFilePath(filePath.toString());
//            image.setFileSize(file.getSize());
//            image.setMediaType(file.getContentType());
//            image.setData(file.getBytes());
//            imageRepository.save(image);
//        }
//    }
//    //    file name gets extended
//    private String getExtension(String fileName) {
//        return fileName.substring(fileName.lastIndexOf(".") + 1);
//    }
//    public Optional<UserEntity> findOne(Integer id) {
//        return userRepository.findById(id);
//    }
//    }
//
//
