package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdsMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AdServiceImpl {
    private final AdRepository adRepository;
    private final AdsMapper adsMapper;

    @Autowired
    public AdServiceImpl(AdRepository adRepository, AdsMapper adsMapper) {
        this.adRepository = adRepository;
        this.adsMapper = adsMapper;
    }

    @Value("${image.dir.path}")
    private String imagesDir;

    //    uploads file
//    public void uploadImage(Long id, MultipartFile file) throws IOException {
//
//        Ad adEntity = findOne(id);
//        Path filePath = Path.of("./image", id + "." + getExtension(file.getOriginalFilename()));
//        Files.createDirectories(filePath.getParent());
//        Files.deleteIfExists(filePath);
//        try (InputStream is = file.getInputStream();
//             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
//             BufferedInputStream bis = new BufferedInputStream(is, 1024);
//             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
//        ) {
//            bis.transferTo(bos);
//        }
//        AdEntity adEntity = adRepository.findById(id).orElseGet(AdEntity::new);
//        adEntity.setAuthor(adEntity.getAuthor());
//        adEntity.setImage(file.toString());
//        adRepository.save(adEntity);
//    }
//
//    //    file name gets extended
//    private String getExtension(String fileName) {
//        return fileName.substring(fileName.lastIndexOf(".") + 1);
//    }
//
//    public Optional<AdEntity> findOne(long id) {
//        return adRepository.findById(id);
//    }


    public Ads getAllInfoAboutAds() {
        List<Ad> collect = adRepository.findAll().stream().map(e -> adsMapper.adsToDto(e)).collect(Collectors.toList());

//            Ad ad = new Ad();
//            ad.setAuthor(ad.getAuthor());
//            ad.setPk(ad.getPk());
//            ad.setImage(ad.getImage());
//            ad.setTitle(ad.getTitle());
//            ad.setPrice(ad.getPrice());
//            return ad;
//        }).collect(Collectors.toList());
        return new Ads(collect.size(), collect);
    }

//        public CreateOrUpdateAd createAd (Long id, MultipartFile image){
//        uploadImage(id, image);
//        return adRepository.save();
//    }



    public ExtendedAd getInfoExtendedAdById(Long id) {
           List <ExtendedAd> collect = adRepository.findById(id).stream().map(e -> adsMapper.extendAdToDto(e)).collect(Collectors.toList());
            return (ExtendedAd) collect;
        }


    public AdEntity deleteInfoAboutAdById(Long id) {
        return adRepository.deleteById(id);
    }


    public CreateOrUpdateAd updateAd(Long id, CreateOrUpdateAd updateAd) {
           CreateOrUpdateAd collect = adRepository.findById(id).stream().map(e -> {
                CreateOrUpdateAd ad = new Ad();
                ad.setTitle(ad.getTitle());
                ad.setPrice(ad.getPrice());
                ad.setDescription(ad.getDescription());
                return ad;
            }).collect(Collectors.toList());
           collect =  adRepository.save();
            return collect;
        }


    public Ads getMe() {
            List<Ad> collect = adRepository.findAll().stream().map(e -> {
                Ad ad = new Ad();
                ad.setAuthor(ad.getAuthor());
                ad.setPk(ad.getPk());
                ad.setImage(ad.getImage());
                ad.setTitle(ad.getTitle());
                ad.setPrice(ad.getPrice());
                return ad;
            }).collect(Collectors.toList());
            return new Ads(collect.size(),collect);
        }


    public ResponseEntity<String> uploadImage(Long id, MultipartFile image) throws
            IOException {
        return adRepository.save(id, image);

    }
}
}