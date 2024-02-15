package ru.skypro.homework.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.TargetType;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.CommentEntity;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface CommentsMapper {
    @Mapping(target = "pk",source = "id")
    @Mapping(target = "author",expression = "java(commentEntity.getAuthor().getId())")
    Comment commentsToDto(CommentEntity commentEntity);
    @Mapping(target = "pk",source = "id")
    CreateOrUpdateComment updateCommentToDto(CommentEntity commentEntity);

}

