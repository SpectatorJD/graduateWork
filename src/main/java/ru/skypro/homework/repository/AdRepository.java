package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.Optional;

public interface AdRepository extends JpaRepository<AdEntity, Integer> {
    Optional<AdEntity> findByUsersId(Integer id);
}
