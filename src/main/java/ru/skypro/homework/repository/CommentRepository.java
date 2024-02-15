package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.entity.CommentEntity;

import java.util.Optional;


public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
@Query(value = "select * from comments where id=:commentId and ads_id=:adId" , nativeQuery = true)
    Optional<CommentEntity> findByIdAndAdsId(Integer commentId,Integer adId);
}
