package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.mappers.CommentsMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentsMapper commentsMapper;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final LocalDateTime today = LocalDateTime.now();
    DateTimeFormatter dateAndTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    //Получение комментариев объявления
    public Comments getInfoAboutCommitById(Integer id) {
        List<CommentEntity> comments = adRepository.findById(id).get().getComments();
        List<Comment> collect = comments.stream().map(e -> commentsMapper.commentsToDto(e)).collect(Collectors.toList());
        return new Comments(collect.size(), collect);
    }

    //Добавление комментария к объявлению
    public CreateOrUpdateComment createComment(Integer id, CreateOrUpdateComment createComment, Authentication authentication) {
        UserEntity userEntity = userRepository.findByEmail(authentication.getName()).get();
        AdEntity adEntity = adRepository.findById(id).get();
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setAuthor(userEntity);
        commentEntity.setCreateAt(LocalDateTime.parse(today.format(dateAndTime)));
        commentEntity.setAds(adEntity);
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
        CommentEntity save = commentRepository.save(commentEntity);
        return commentsMapper.updateCommentToDto(save);
    }
}

