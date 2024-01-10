package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
//import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdRepository;
//import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.mappers.AdsMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@RequiredArgsConstructor
@Service
public class AdService {

    private final AdRepository adRepository;
    private final AdsMapper adsMapper;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    @Value("${image.dir.path}")
    private String imagesDir;

    private String objectAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    //Получение всех объявлений
    public Ads getAllInfoAboutAds() {
        List<Ad> collect = adRepository.findAll().stream().map(adsMapper::adsToDto).collect(Collectors.toList());
        return new Ads(collect.size(), collect);
    }

//    Добавление объявления
    public CreateOrUpdateAd createAd(Authentication authentication, CreateOrUpdateAd createAd, MultipartFile file) throws IOException {
        UserEntity userEntity = userRepository.findByEmail(authentication.getName()).get();
        AdEntity adEntity = new AdEntity();
        adEntity.setTitle(createAd.getTitle());
        adEntity.setPrice(createAd.getPrice());
        adEntity.setDescription(createAd.getDescription());
        adEntity.setUsers(userEntity);
        adRepository.save(adEntity);

        Path filePath = Path.of("./image",  "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        ImageEntity image = imageRepository.findById(adEntity.getId()).orElseGet(ImageEntity::new);
        image.setAds(adEntity);
        image.setFileSize(file.getSize());
        image.setMediaType(file.getContentType());
        image.setData(file.getBytes());
        imageRepository.save(image);
//        adEntity.getImages(image);
        adRepository.save(adEntity);
        return adsMapper.updateAdToDto(adEntity);


    }

    //Получение информации об объявлении
    public ExtendedAd getInfoExtendedAdById(Integer id) {
        List<ExtendedAd> collect = adRepository.findById(id).stream().map(adsMapper::extendAdToDto).collect(Collectors.toList());
        return (ExtendedAd) collect;
    }

    //Удаление объявления
    public void deleteInfoAboutAdById(Integer id) {
        adRepository.deleteById(id);
    }

    //Обновление информации об объявлении
    public CreateOrUpdateAd updateAd(Integer id, CreateOrUpdateAd updateAd) {
        AdEntity adEntity = adRepository.findById(id).get();
        adEntity.setTitle(updateAd.getTitle());
        adEntity.setPrice(updateAd.getPrice());
        adEntity.setDescription(updateAd.getDescription());
        adRepository.save(adEntity);
        return adsMapper.updateAdToDto(adEntity);
    }

    //Получение объявлений авторизованного пользователь
    public Ads getMe(Authentication authentication) {
            UserEntity userEntity = userRepository.findByEmail(authentication.getName()).get();
            List<Ad> collect = adRepository.findByUsersId(userEntity.getId()).stream().map(e -> {
                Ad ad = new Ad();
                ad.setAuthor(e.getUsers().getId());
                ad.setPk(e.getId());
                ad.setImage("/ads/" + e.getId() + "/image");
                ad.setTitle(e.getTitle());
                ad.setPrice(e.getPrice());
                return ad;
            }).collect(Collectors.toList());
            return new Ads(collect.size(),collect);
        }

//     Обновление картинки объявления
    public void uploadImage(Integer adId, MultipartFile file) throws IOException {
        AdEntity ad = findAd(adId);
        Path filePath = Path.of("./image", adId + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        ImageEntity image = imageRepository.findById(adId).orElseGet(ImageEntity::new);
        image.setAds(ad);
        image.setFileSize(file.getSize());
        image.setMediaType(file.getContentType());
        image.setData(file.getBytes());
        imageRepository.save(image);
    }


    //имя файла расширяется
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    //находит объявление по идентификатору
    public Optional<AdEntity> findOne(Integer id) {
        return adRepository.findById(id);
    }

    //находит объявление по идентификатору
    public AdEntity findAd(Integer id) {
        return adRepository.findById(id).get();
    }
}
