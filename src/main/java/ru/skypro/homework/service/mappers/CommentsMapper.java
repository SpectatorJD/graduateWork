package ru.skypro.homework.service.mappers;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;

@Mapper(componentModel = "spring")
public interface CommentsMapper {
    Comment commentsToDto(CommentEntity commentEntity);

//    ExtendedAd extendAdToDto(CommentEntity commentEntity);

    CreateOrUpdateComment updateCommentToDto(CommentEntity commentEntity);
}

