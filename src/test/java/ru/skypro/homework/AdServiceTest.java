//package ru.skypro.homework;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.web.servlet.MockMvc;
//import ru.skypro.homework.dto.Ad;
//import ru.skypro.homework.dto.Ads;
//import ru.skypro.homework.dto.CreateOrUpdateAd;
//import ru.skypro.homework.entity.AdEntity;
//import ru.skypro.homework.entity.Image;
//import ru.skypro.homework.repository.AdRepository;
//import ru.skypro.homework.repository.ImageRepository;
//import ru.skypro.homework.service.AdService;
//import ru.skypro.homework.service.mappers.AdsMapper;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class AdServiceTest {
//
//    private AdService out;
//    private AdRepository adRepository;
//    private  AdsMapper adsMapper;
//    private  ImageRepository imageRepository;
//
//    @BeforeEach
//    public void setUp() {
//        adRepository = mock(AdRepository.class);
//        adsMapper = mock(AdsMapper.class);
//        imageRepository = mock(ImageRepository.class);
//        out = new AdService(adRepository,adsMapper,imageRepository);
//
//    }
////    public ResponseEntity<Ads> getAllInfoAboutAds() {
////        Ads ads = adService.getAllInfoAboutAds();
////        return ResponseEntity.ok(ads);
//
//    private List<Ad> ad() {
//        return List.of(new Ad(1, " image", 1, 85," title"));
//    }
//    private List<Ads> ads() {
//        return List.of(new Ads(1, ad()));
//    }
//    private List<CreateOrUpdateAd> createOrUpdateAds() {
//        return List.of(new CreateOrUpdateAd("title", 1, "description"));
//    }
//    private List<Image> images() {
//        return List.of(new Image(1, 1L, "media type", 4 , 1,1));
//    }
//
//
//    @Test
//    public void getAllInfoAboutAdsTest() {
//        AdEntity adEntity = new AdEntity();
//        when(adRepository.findAll()).thenReturn(ad());
//        when(adsMapper.adsToDto(adEntity)).thenReturn(ads())
//        assertIterableEquals(ads(), out.getAllInfoAboutAds());
//        verify(adRepository, times(1)).findAll();
//    }
//    @Test
//    public void createAdTest() {
//        AdEntity adEntity = new AdEntity();
//        when(adRepository.save(createOrUpdateAds())).thenReturn(createOrUpdateAds());
//        when(imageRepository.save(createOrUpdateAds())).thenReturn(createOrUpdateAds());
//        when(adsMapper.adsToDto(adEntity)).thenReturn(ads());
//        MockMultipartFile file
//                = new MockMultipartFile(
//                "file",
//                "hello.txt",
//                MediaType.TEXT_PLAIN_VALUE,
//                "Hello, World!".getBytes()
//        );
//        assertIterableEquals(ads(), out.createAd(createOrUpdateAds()));
//        verify(adRepository, times(1)).findAll();
//    }
//}
