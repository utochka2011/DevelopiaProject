package com.example.developia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.developia.entity.BasketEntity;

public interface BasketRepository extends JpaRepository<BasketEntity, Long> {
    List<BasketEntity> findByUserId(Long userId);
    Optional<BasketEntity> findByUserIdAndClothesId(Long userId, Long clothesId);
    void deleteByUserIdAndClothesId(Long userId, Long clothesId);
}
