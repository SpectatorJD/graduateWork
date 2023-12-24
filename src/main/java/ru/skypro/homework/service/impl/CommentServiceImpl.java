package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.repository.CommentRepository;

import java.util.Collection;
@Service
public class CommentServiceImpl {
    private final CommentRepository commentRepository;
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    //    GET
///ads/{id}/comments
//Получение комментариев объявления
//        Parameters
//Cancel
//Name	Description
//id *
//integer($int32)
//(path)
//id
//Execute
//Responses
//Code	Description	Links
//200
//OK
//
//Media type
//
//application/json
//Controls Accept header.
//Example Value
//Schema
//{
//  "count": 0,
//  "results": [
//    {
//      "author": 0,
//      "authorImage": "string",
//      "authorFirstName": "string",
//      "createdAt": 0,
//      "pk": 0,
//      "text": "string"
//    }
//  ]
//}
//No links
//401
//Unauthorized
//
//No links
//404
//Not found
//    @GetMapping("/{id}/comments")
    public Comment getInfoAboutCommitById (Long id){
        return commentRepository.findById(id);


    //POST
///ads/{id}/comments
//Добавление комментария к объявлению
//        POST
///ads/{id}/comments
//Добавление комментария к объявлению
//Jump to definition
//Parameters
//Cancel
//Name	Description
//id *
//integer($int32)
//(path)
//id
//Request body
//
//application/json
//{
//  "text": "stringst"
//}
//Execute
//Responses
//Code	Description	Links
//200
//OK
//
//Media type
//
//application/json
//Controls Accept header.
//Example Value
//Schema
//{
//  "author": 0,
//  "authorImage": "string",
//  "authorFirstName": "string",
//  "createdAt": 0,
//  "pk": 0,
//  "text": "string"
//}
//No links
//401
//Unauthorized
//
//No links
//404
//Not found
//    @PostMapping("/{id}/comments")
    public CreateOrUpdateComment createComment ( Long id, CreateOrUpdateComment createComment){
        return commentRepository.save(createComment);
    }

    //DELETE
///ads/{adId}/comments/{commentId}
//Удаление комментария
//        Parameters
//Cancel
//Name	Description
//adId *
//integer($int32)
//(path)
//adId
//commentId *
//integer($int32)
//(path)
//commentId
//Execute
//Responses
//Code	Description	Links
//200
//OK
//
//No links
//401
//Unauthorized
//
//No links
//403
//Forbidden
//
//No links
//404
//Not found
//    @DeleteMapping("/{adId}/comments")
    public ru.skypro.homework.entity.Comment deleteInfoAboutAdById (Long adId){
      return commentRepository.deleteById(id);

    }

    //PATCH
///ads/{adId}/comments/{commentId}
//Обновление комментария
//        Parameters
//Cancel
//Name	Description
//adId *
//integer($int32)
//(path)
//adId
//commentId *
//integer($int32)
//(path)
//commentId
//Execute
//Responses
//Code	Description	Links
//200
//OK
//
//No links
//401
//Unauthorized
//
//No links
//403
//Forbidden
//
//No links
//404
//Not found
//    @PatchMapping("/{adId}/comments")
    public CreateOrUpdateComment UpdateComment ( Long id, CreateOrUpdateComment UpdateComment){
        return commentRepository.save(updateComment);
}
}

