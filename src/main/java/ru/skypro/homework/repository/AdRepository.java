package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.AdEntity;

public interface AdRepository extends JpaRepository<AdEntity, Integer> {
//    Optional<AdEntity> findById(Integer id);

//    void deleteById(Integer id);
}
