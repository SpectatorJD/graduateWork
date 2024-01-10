package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdController {
    private final AdService adService;
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<Ads> getAllInfoAboutAds() {
        Ads ads = adService.getAllInfoAboutAds();
        return ResponseEntity.ok(ads);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<CreateOrUpdateAd> addAd(Authentication authentication, @RequestPart(value = "properties", required = true) CreateOrUpdateAd properties,
                                           @RequestPart(value = "image", required = true) MultipartFile image) throws IOException {
        CreateOrUpdateAd createAd = adService.createAd(authentication,properties, image);
        return ResponseEntity.ok(createAd);
    }

    @GetMapping("{id}")
    public ResponseEntity<ExtendedAd> getInfoExtendedAdById(@PathVariable Integer id) {
        ExtendedAd extendedAd = adService.getInfoExtendedAdById(id);
        if (extendedAd == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(extendedAd);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Ad> deleteInfoAboutAdById(@PathVariable Integer id) {
        Optional<AdEntity> adEntity = adService.findOne(id);
        if (adEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        adService.deleteInfoAboutAdById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<CreateOrUpdateAd> updateAd(@PathVariable Integer id, @RequestBody CreateOrUpdateAd updateAd) {
        CreateOrUpdateAd foundAd = adService.updateAd(id, updateAd);
        if (foundAd == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundAd);
    }

        @GetMapping("/me")
        public ResponseEntity<Ads> getMe (Authentication authentication) {
        Ads ads = adService.getMe(authentication);
        return ResponseEntity.ok(ads);
        }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdEntity> updateImage(@PathVariable Integer id, @RequestParam MultipartFile image) throws
            IOException {
        Optional<AdEntity> adEntity = adService.findOne(id);
        if (adEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        adService.uploadImage(id, image);
        return ResponseEntity.ok().build();
    }

//           Comments

    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getInfoAboutCommitById(@PathVariable Integer id) {
        Comments comments = commentService.getInfoAboutCommitById(id);
        if (comments == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CreateOrUpdateComment> createComment(@PathVariable Integer id, @RequestBody CreateOrUpdateComment
            createComment) {
        Optional<AdEntity> adEntity = adService.findOne(id);
        if (adEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        CreateOrUpdateComment comment = commentService.createComment(id, createComment);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> deleteInfoAboutCommentById(@PathVariable Integer adId,
                                                              @PathVariable Integer commentId) {
        Optional<AdEntity> adEntity = adService.findOne(adId);
        if (adEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        commentService.deleteInfoAboutCommentById(adId, commentId);
        return ResponseEntity.ok().build();
    }

        @PatchMapping("/{adId}/comments/{commentId}")
        public ResponseEntity<CreateOrUpdateComment> UpdateComment (@PathVariable Integer adId,
            @PathVariable Integer commentId,@RequestBody CreateOrUpdateComment updateComment){
             CreateOrUpdateComment commentEntity =  commentService.updateComment(adId, commentId, updateComment);
            if (commentEntity == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok(updateComment);
        }
}

