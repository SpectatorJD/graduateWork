package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.mappers.CommentsMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl {
    private final CommentRepository commentRepository;
    private final CommentsMapper commentsMapper;

    //Получение комментариев объявления
    public Comments getInfoAboutCommitById(Integer id) {
        List<Comment> collect = commentRepository.findById(id).stream().map(e -> commentsMapper.commentsToDto(e)).collect(Collectors.toList());
        return new Comments(collect.size(), collect);
    }

    //Добавление комментария к объявлению
    public CreateOrUpdateComment createComment(Integer id, CreateOrUpdateComment createComment) {
        CommentEntity commentEntity = commentRepository.findById(id).get();
        commentEntity.setText(createComment.getText());
        commentRepository.save(commentEntity);
        return commentsMapper.updateCommentToDto(commentEntity);

    }

    //Удаление комментария
    public void deleteInfoAboutCommentById(Integer adId, Integer commentId) {
        CommentEntity commentEntity = commentRepository.findByIdAndAdsId(commentId, adId).orElseThrow();
        commentRepository.delete(commentEntity);

    }

    //Обновление комментария
    public CreateOrUpdateComment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment updateComment) {
        CommentEntity commentEntity = commentRepository.findByIdAndAdsId(commentId, adId).orElseThrow();
        commentEntity.setText(updateComment.getText());
        commentRepository.save(commentEntity);
        return commentsMapper.updateCommentToDto(commentEntity);
    }
}

