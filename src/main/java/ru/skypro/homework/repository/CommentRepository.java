package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;
import java.util.Map;


public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findById(Integer id);

    void deleteById(Integer adId, Integer commentId);

    List <CommentEntity> findByAdIdAndCommentId(Integer adId, Integer commentId);

}
