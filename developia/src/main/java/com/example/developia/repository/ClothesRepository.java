package com.example.developia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.developia.entity.ClothesEntity;

@Repository
public interface ClothesRepository extends JpaRepository<ClothesEntity, Long> {
    List<ClothesEntity> findByOwnerId(Long ownerId);

    List<ClothesEntity> findAllByOrderByPriceAsc();

    List<ClothesEntity> findAllByOrderByPriceDesc();

    List<ClothesEntity> findByBrandContainingIgnoreCase(String brand);

    List<ClothesEntity> findAllByRatingGreaterThanEqual(Integer minRating);
}
