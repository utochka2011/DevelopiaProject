package com.example.developia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.developia.entity.ClothesEntity;
import com.example.developia.entity.UserEntity;
import com.example.developia.jwt.SecurityUtil;
import com.example.developia.repository.ClothesRepository;
import com.example.developia.repository.UserRepository;
import com.example.developia.request.ClothesRequest;

@Service
public class ClothesService {

    @Autowired
    private ClothesRepository clothesRepository;

    @Autowired
    private UserRepository userRepository;

    public void addClothesForCurrentUser(ClothesRequest request) {
        String username = SecurityUtil.getCurrentUsername();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ClothesEntity clothes = new ClothesEntity();
        clothes.setBrand(request.getBrand());
        clothes.setModel(request.getModel());
        clothes.setDescription(request.getDescription());
        clothes.setPrice(request.getPrice());
        clothes.setSize(request.getSize());
        clothes.setImgUrl(request.getImgUrl());
        clothes.setRating(request.getRating());
        clothes.setOwnerId(user.getId());

        clothesRepository.save(clothes);
    }

    public List<ClothesEntity> getMyClothes() {
        String username = SecurityUtil.getCurrentUsername();
        Long userId = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found")).getId();
        return clothesRepository.findByOwnerId(userId);
    }

    public ClothesEntity getMyClothesById(Long id) {
        String username = SecurityUtil.getCurrentUsername();
        Long currentUserId = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found")).getId();

        ClothesEntity clothes = clothesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clothes not found"));

        if (!clothes.getOwnerId().equals(currentUserId)) {
            throw new RuntimeException("Access denied");
        }
        return clothes;
    }

    public void deleteClothes(Long id) {
        ClothesEntity clothes = getMyClothesById(id);
        clothesRepository.delete(clothes);
    }

    public List<ClothesEntity> getAllClothes() {
        return clothesRepository.findAll();
    }

    public Optional<ClothesEntity> getClothesById(Long id) {
        return clothesRepository.findById(id);
    }

    public List<ClothesEntity> searchClothes(String keyword) {
        return clothesRepository.findByBrandContainingIgnoreCase(keyword);
    }

    public List<ClothesEntity> filterByMinRating(Integer minRating) {
        return clothesRepository.findAllByRatingGreaterThanEqual(minRating);
    }

    public List<ClothesEntity> sortByPriceAsc() {
        return clothesRepository.findAllByOrderByPriceAsc();
    }

    public List<ClothesEntity> sortByPriceDesc() {
        return clothesRepository.findAllByOrderByPriceDesc();
    }

    public void updateClothes(Long id, ClothesRequest request) {
        ClothesEntity existing = getMyClothesById(id);
        existing.setBrand(request.getBrand());
        existing.setModel(request.getModel());
        existing.setDescription(request.getDescription());
        existing.setPrice(request.getPrice());
        existing.setSize(request.getSize());
        existing.setImgUrl(request.getImgUrl());
        existing.setRating(request.getRating());
        clothesRepository.save(existing);
    }
}
