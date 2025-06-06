package com.example.developia.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.developia.entity.BasketEntity;
import com.example.developia.entity.ClothesEntity;
import com.example.developia.entity.UserEntity;
import com.example.developia.jwt.SecurityUtil;
import com.example.developia.repository.BasketRepository;
import com.example.developia.repository.ClothesRepository;
import com.example.developia.repository.UserRepository;
import com.example.developia.request.BasketResponse;

import jakarta.transaction.Transactional;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClothesRepository clothesRepository;

    public void addToBasket(Long clothesId) {
        String username = SecurityUtil.getCurrentUsername();
        UserEntity user = userRepository.findByUsername(username).orElseThrow();
        
        BasketEntity basket = basketRepository.findByUserIdAndClothesId(user.getId(), clothesId)
                .orElse(new BasketEntity());
        
        basket.setUserId(user.getId());
        basket.setClothesId(clothesId);
        
        if (basket.getQuantity() == null) {
            basket.setQuantity(1);
        } else {
            basket.setQuantity(basket.getQuantity() + 1);
        }
        
        basketRepository.save(basket);
    }

    @Transactional
    public void removeFromBasket(long clothesId) {
        String username = SecurityUtil.getCurrentUsername();
        UserEntity user = userRepository.findByUsername(username).orElseThrow();
        basketRepository.deleteByUserIdAndClothesId(user.getId(), clothesId);
    }

    public List<BasketResponse> getMyBasket() {
        String username = SecurityUtil.getCurrentUsername();
        UserEntity user = userRepository.findByUsername(username).orElseThrow();
        List<BasketEntity> baskets = basketRepository.findByUserId(user.getId());

        return baskets.stream()
                .map(basket -> {
                    ClothesEntity clothesEntity = clothesRepository.findById(basket.getClothesId()).orElse(null);
                    if (clothesEntity.getId() == null) {
                        return null;
                    }

                    
                    
                    BasketResponse basketResponse = new BasketResponse();
                    basketResponse.setId(clothesEntity.getId());
                    basketResponse.setBrand(clothesEntity.getBrand());
                    basketResponse.setModel(clothesEntity.getModel());
                    basketResponse.setDescription(clothesEntity.getDescription());
                    basketResponse.setSize(clothesEntity.getSize());
                    basketResponse.setPrice(clothesEntity.getPrice());
                    basketResponse.setImgUrl(clothesEntity.getImgUrl());
                    basketResponse.setRating(clothesEntity.getRating());
                    basketResponse.setOwnerId(clothesEntity.getOwnerId());
                    basketResponse.setQuantity(basket.getQuantity());

                    return basketResponse;
                })
                .collect(Collectors.toList());
    }
}
