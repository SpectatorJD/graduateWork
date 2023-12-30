package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.mappers.AdsMapper;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

//@RequiredArgsConstructor
@Service
public class AdServiceImpl {

    private final AdRepository adRepository;
    private final AdsMapper adsMapper;
    private final ImageRepository imageRepository;

    public AdServiceImpl(AdRepository adRepository, AdsMapper adsMapper, ImageRepository imageRepository) {
        this.adRepository = adRepository;
        this.adsMapper = adsMapper;
        this.imageRepository = imageRepository;

    }

    @Value("${image.dir.path}")
    private String imagesDir;

    //Получение всех объявлений
    public Ads getAllInfoAboutAds() {
        List<Ad> collect = adRepository.findAll().stream().map(adsMapper::adsToDto).collect(Collectors.toList());
        return new Ads(collect.size(), collect);
//        List<Ad> collect = adRepository.findAll().stream().map(e -> {
//            Ad ad = new Ad();
//            ad.setAuthor(ad.getAuthor());
//            ad.setPk(ad.getPk());
//            ad.setImage(ad.getImage());
//            ad.setTitle(ad.getTitle());
//            ad.setPrice(ad.getPrice());
//            return ad;
//        }).collect(Collectors.toList());
//        return new Ads(collect.size(), collect);
    }

    //Добавление объявления
    public CreateOrUpdateAd createAd(CreateOrUpdateAd createAd, MultipartFile image) throws IOException {

//        Path filePath = Path.of("./image", "." + getExtension(image.getOriginalFilename()));
//        Files.createDirectories(filePath.getParent());
//        Files.deleteIfExists(filePath);
//        try (InputStream is = image.getInputStream();
//             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
//             BufferedInputStream bis = new BufferedInputStream(is, 1024);
//             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
//        ) {
//            bis.transferTo(bos);
//        }

        AdEntity adEntity = new AdEntity();
        adEntity.setTitle(createAd.getTitle());
        adEntity.setPrice(createAd.getPrice());
        adEntity.setDescription(createAd.getDescription());
//        adEntity.setImage(image.getBytes());
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

    //Получение объявлений авторизованного пользовател
//    public Ads getMe(Authentication authentication) {
//            List<Ad> collect = adRepository.findById(authentication.).stream().map(e -> {
//                Ad ad = new Ad();
//                ad.setAuthor(ad.getAuthor());
//                ad.setPk(ad.getPk());
//                ad.setImage(ad.getImage());
//                ad.setTitle(ad.getTitle());
//                ad.setPrice(ad.getPrice());
//                return ad;
//            }).collect(Collectors.toList());
//            return new Ads(collect.size(),collect);
//        }

    // Обновление картинки объявления
    public void uploadImage(Integer adId, MultipartFile file) throws
            IOException {
        AdEntity ad = findAd(adId);
        Path filePath = Path.of("./avatar", adId + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Image image = imageRepository.findByAdId(adId).orElseGet(Image::new);
        image.setAdEntity(ad);
        image.setFilePath(filePath.toString());
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
