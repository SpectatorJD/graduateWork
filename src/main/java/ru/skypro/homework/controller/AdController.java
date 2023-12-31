package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.impl.AdServiceImpl;
import ru.skypro.homework.service.impl.CommentServiceImpl;

import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdController {
    private final AdRepository adRepository;
    private final AdServiceImpl adService;
    private final CommentServiceImpl commentService;


    @Operation(summary = "Получение всех объявлений")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Ads.class))))})

    @GetMapping
    public ResponseEntity<Ads>getAllInfoAboutAds() {
        Ads ads = adService.getAllInfoAboutAds();
        return ResponseEntity.ok(ads);
    }

        @Operation(summary = "Добавление объявления")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Created",
                        content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                array = @ArraySchema(schema = @Schema(implementation = Ad.class)))),
                @ApiResponse(responseCode = "401", description = "Unauthorized")})

        @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        ResponseEntity<CreateOrUpdateAd> addAd(@RequestPart(value = "properties", required = true) CreateOrUpdateAd properties,
                                 @RequestPart(value = "image", required = true) MultipartFile image) {
                CreateOrUpdateAd createAd = adService.createAd(properties,image);
                return ResponseEntity.ok(createAd);
            }



        @Operation(summary = "Получение информации об объявлении")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "OK",
                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                array = @ArraySchema(schema = @Schema(implementation = ExtendedAd.class)))),
                @ApiResponse(responseCode = "401", description = "Unauthorized"),
                @ApiResponse(responseCode = "404", description = "Not found")})

        @GetMapping("{id}")
        public ResponseEntity<ExtendedAd> getInfoExtendedAdById (@PathVariable Integer id){
            ExtendedAd extendedAd = adService.getInfoExtendedAdById(id);
            if (extendedAd == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(extendedAd);
        }


        @Operation(summary = "Удаление объявления")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "204", description = "No Content"),
                @ApiResponse(responseCode = "401", description = "Unauthorized"),
                @ApiResponse(responseCode = "403", description = "Forbidden"),
                @ApiResponse(responseCode = "404", description = "Not found")})

        @DeleteMapping("{id}")
        public ResponseEntity<Ad> deleteInfoAboutAdById (@PathVariable Integer id){
            adService.deleteInfoAboutAdById(id);
            return ResponseEntity.ok().build();
        }

        @Operation(summary = "Обновление информации об объявлении")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "OK",
                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                array = @ArraySchema(schema = @Schema(implementation = Ad.class)))),
                @ApiResponse(responseCode = "401", description = "Unauthorized"),
                @ApiResponse(responseCode = "403", description = "Forbidden"),
                @ApiResponse(responseCode = "404", description = "Not found")})

        @PatchMapping("{id}")
        public ResponseEntity<CreateOrUpdateAd> updateAd (@PathVariable Integer id, @RequestBody CreateOrUpdateAd updateAd){
            CreateOrUpdateAd foundAd = adService.updateAd(id,updateAd);
            if (foundAd == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok(foundAd);
        }

        @Operation(summary = "Получение объявлений авторизованного пользователя")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "OK",
                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                array = @ArraySchema(schema = @Schema(implementation = Ads.class)))),
                @ApiResponse(responseCode = "401", description = "Unauthorized")})
        @GetMapping("/me")
        public ResponseEntity<Ads> getMe (Authentication authentication) {
        Ads ads = adService.getMe(authentication);
        return ResponseEntity.ok(ads);
        }

        @Operation(summary = "Обновление картинки объявления")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "OK",
                        content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                                array = @ArraySchema(schema = @Schema(implementation = "String")))),
                @ApiResponse(responseCode = "401", description = "Unauthorized"),
                @ApiResponse(responseCode = "403", description = "Forbidden"),
                @ApiResponse(responseCode = "404", description = "Not found")})

        @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<String> uploadImage (@PathVariable Integer id, @RequestParam MultipartFile image) throws
                IOException {
            adService.uploadImage(id, image);
            return ResponseEntity.ok().build();
        }

        //Comments

        @Operation(summary = "Получение комментариев объявления")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Ok",
                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                array = @ArraySchema(schema = @Schema(implementation = Comments.class)))),
                @ApiResponse(responseCode = "401", description = "Unauthorized"),
                @ApiResponse(responseCode = "404", description = "Not found")})

        @GetMapping("/{id}/comments")
        public ResponseEntity<Comments> getInfoAboutCommitById (@PathVariable Integer id){
            Comments comments = commentService.getInfoAboutCommitById(id);
            if (comments == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(comments);
        }


        @Operation(summary = "Добавление комментария к объявлению")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "OK",
                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                array = @ArraySchema(schema = @Schema(implementation = Comment.class)))),
                @ApiResponse(responseCode = "401", description = "Unauthorized"),
                @ApiResponse(responseCode = "404", description = "Not found")})
        @PostMapping("/{id}/comments")
        public ResponseEntity<CreateOrUpdateComment> createComment (@PathVariable Integer id, @RequestBody CreateOrUpdateComment
        createComment){
            CreateOrUpdateComment comment = commentService.createComment(id, createComment);
            return ResponseEntity.ok(comment);
        }

        @Operation(summary = "Удаление комментария")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "OK"),
                @ApiResponse(responseCode = "401", description = "Unauthorized"),
                @ApiResponse(responseCode = "403", description = "Forbidden"),
                @ApiResponse(responseCode = "404", description = "Not found")})

        @DeleteMapping("/{adId}/comments/{commentId}")
        public ResponseEntity<Comment> deleteInfoAboutCommentById (@PathVariable Integer adId, @PathVariable Integer commentId){
            commentService.deleteInfoAboutCommentById(adId, commentId);
            return ResponseEntity.ok().build();
        }

        @Operation(summary = "Обновление комментария")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "OK",
                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                array = @ArraySchema(schema = @Schema(implementation = Comment.class)))),
                @ApiResponse(responseCode = "401", description = "Unauthorized"),
                @ApiResponse(responseCode = "403", description = "Forbidden"),
                @ApiResponse(responseCode = "404", description = "Not found")})

        @PatchMapping("/{adId}/comments/{commentId}")
        public ResponseEntity<CreateOrUpdateComment> UpdateComment (@PathVariable Integer adId,
            @PathVariable Integer commentId,@RequestBody CreateOrUpdateComment updateComment){
             CommentEntity commentEntity = (CommentEntity) commentService.updateComment(adId, commentId, updateComment);
            if (commentEntity == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok(updateComment);
        }
    }

