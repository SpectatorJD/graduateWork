package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.mappers.CommentsMapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl {
    private final CommentRepository commentRepository;
    private final CommentsMapper commentsMapper;


    public Comments getInfoAboutCommitById (Integer id){
            List<Comment> collect = commentRepository.findById(id).stream().map(e -> commentsMapper.commentsToDto(e)).collect(Collectors.toList());

//            Ad ad = new Ad();
//            ad.setAuthor(ad.getAuthor());
//            ad.setPk(ad.getPk());
//            ad.setImage(ad.getImage());
//            ad.setTitle(ad.getTitle());
//            ad.setPrice(ad.getPrice());
//            return ad;
//        }).collect(Collectors.toList());
            return new Comments(collect.size(), collect);
        }

    public CreateOrUpdateComment createComment (Integer id, CreateOrUpdateComment createComment){
        CommentEntity commentEntity = commentRepository.findById(id).get();
        commentEntity.setText(createComment.getText());
        commentRepository.save(commentEntity);
        return  commentsMapper.updateCommentToDto(commentEntity);

    }

    public void deleteInfoAboutCommentById (Integer adId, Integer commentId){
      commentRepository.deleteById(adId, commentId);

    }


    public List<CreateOrUpdateComment> updateComment (Integer adId, Integer commentId,CreateOrUpdateComment updateComment){
        CommentEntity commentEntity = commentRepository.findByAdIdAndCommentId(adId, commentId).get();
        commentEntity.setText(updateComment.getText());
        commentRepository.save(commentEntity);
        return commentsMapper.updateCommentToDto(commentEntity);
}
}

