package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class Ads {
    //GET
    ///ads
    //Получение всех объявлений
    @GetMapping
    public ResponseEntity<Collection<Ads>> getAllInfoAboutAds(@RequestBody Ads ads) {
        return ResponseEntity.ok(adsService.findByAgeBetween(ads));

        //POST
        ///ads
        //Добавление объявления
        @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE) {
            public CreateOrUpdateAd createAd (@RequestBody CreateOrUpdateAd createUpdate,
                @RequestParam MultipartFile ad)
            {
                createOrUpdateService.addAd(createAd);
                adsService.uploadAd(ad);
                return ResponseEntity.ok().build();
            }
        }


        //Получение информации об объявлении
        @GetMapping("{id}")
        public ResponseEntity<ExtendedAd> getInfoExtendedAdById (@PathVariable Long id){
            ExtendedAd extendedAd = adsService.getAd(id);
            if (extendedAd == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(extendedAd);
        }


        //Удаление объявления

        @DeleteMapping("{id}")
        public ResponseEntity<Ad> deleteInfoAboutAdById (@PathVariable Long id){
            adsService.removeStudent(id);
            return ResponseEntity.ok().build();
        }

        //PATCH
        ///ads/{id}
        //Обновление информации об объявлении
        @PatchMapping("{id}")
        public ResponseEntity<Ad> updateAd (@PathVariable Long id, @RequestBody CreateOrUpdateAd updateAd){
            CreateOrUpdateAd foundAd = adsService.getCreatOrUpdateAd(updateAd);
            if (foundAd == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok(updateAd);
        }

        //GET
        ///ads/me
        //Получение объявлений авторизованного пользователя
        @GetMapping("/me")
        public Ads getMe () {
            return ResponseEntity.ok(adsService.getMe());
        }

        //PATCH
//        /ads/{id}/image
//Обновление картинки объявления
        @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<String> uploadImage (@PathVariable Long id, @RequestParam MultipartFile image) throws
        IOException {
            adsService.uploadImage(id, image);
            return ResponseEntity.ok().build();
        }


//    GET
///ads/{id}/comments
//Получение комментариев объявления
        @GetMapping("/{id}/comments")
        public ResponseEntity<Comment> getInfoAboutCommitById (@PathVariable Long id){
            Comment comment = adsService.getCommit(id);
            if (comment == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(comment);
        }

//POST
///ads/{id}/comments
//Добавление комментария к объявлению
        @PostMapping("/{id}/comments")
        public CreateOrUpdateComment createComment (@PathVariable Long id, @RequestBody CreateOrUpdateComment
        createComment){
            return adsService.createComment(createComment);
        }

//DELETE
///ads/{adId}/comments/{commentId}
//Удаление комментария
        @DeleteMapping("/{adId}/comments")
        public ResponseEntity<Collection> deleteInfoAboutAdById (@PathVariable Long adId){
            adsService.deleteComment(id);
            return ResponseEntity.ok().build();
        }

//PATCH
///ads/{adId}/comments/{commentId}
//Обновление комментария
        @PatchMapping("/{adId}/comments")
        public ResponseEntity<CreateOrUpdateComment> UpdateComment (@PathVariable Long id,
            @RequestBody CreateOrUpdateComment UpdateComment){
            CreateOrUpdateComment updateComment = adsService.changeStudent(updateComment);
            if (updateComment == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok(updateComment);
        }
    }
}
