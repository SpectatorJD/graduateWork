package ru.skypro.homework.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;

@Mapper(componentModel = "spring")
public interface CommentsMapper {
    @Mapping(target = "pk",expression = "java(commentEntity.getAdId().getId())")

    @Mapping(target = "author",expression = "java(commentEntity.getAuthor().getId())")
    Comment commentsToDto(CommentEntity commentEntity);

//    ExtendedAd extendAdToDto(CommentEntity commentEntity);

    CreateOrUpdateComment updateCommentToDto(CommentEntity commentEntity);
}

