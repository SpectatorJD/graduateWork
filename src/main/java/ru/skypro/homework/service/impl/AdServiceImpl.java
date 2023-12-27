package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.mappers.AdsMapper;

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

    public AdServiceImpl(AdRepository adRepository, AdsMapper adsMapper) {
        this.adRepository = adRepository;
        this.adsMapper = adsMapper;

    }

//    @Value("${image.dir.path}")
//    private String imagesDir;

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
        public CreateOrUpdateAd createAd (CreateOrUpdateAd createAd, MultipartFile image) throws IOException {

            Path filePath = Path.of("./image",  "." + getExtension(image.getOriginalFilename()));
            Files.createDirectories(filePath.getParent());
            Files.deleteIfExists(filePath);
            try (InputStream is = image.getInputStream();
                 OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                 BufferedInputStream bis = new BufferedInputStream(is, 1024);
                 BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
            ) {
                bis.transferTo(bos);
            }

            AdEntity adEntity = new AdEntity();
            adEntity.setTitle(createAd.getTitle());
            adEntity.setPrice(createAd.getPrice());
            adEntity.setDescription(createAd.getDescription());
            adEntity.setImage(image.getBytes());
            adRepository.save(adEntity);
            return  adsMapper.updateAdToDto(adEntity);


    }

    //Получение информации об объявлении
    public ExtendedAd getInfoExtendedAdById(Integer id) {
           List<ExtendedAd> collect = adRepository.findById(id).stream().map(e -> adsMapper.extendAdToDto(e)).collect(Collectors.toList());
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
        return  adsMapper.updateAdToDto(adEntity);
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
    public Image uploadImage(Integer id, MultipartFile image) throws
            IOException {
        AdEntity adEntityId = findAd(id);
        Path filePath = Path.of("./image", id + "." + getExtension(image.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = image.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Image photo = new Image();
        photo.getImage(image.getBytes());

        AdEntity adEntity = adRepository.findById(id).orElseGet(AdEntity::new);
        adEntity.setImage(photo.getImage());
        adRepository.save(adEntity);
        return adsMapper.updateImageToDto(adEntity);
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
