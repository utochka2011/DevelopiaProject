package com.example.developia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.developia.entity.BasketEntity;
import com.example.developia.entity.UserEntity;
import com.example.developia.jwt.SecurityUtil;
import com.example.developia.repository.BasketRepository;
import com.example.developia.repository.ClothesRepository;
import com.example.developia.repository.UserRepository;

@Service
public class BasketService {
	@Autowired
	private BasketRepository basketRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClothesRepository carRepository;

	public void addToBasket(Long carId) {
		String username = SecurityUtil.getCurrentUsername();
		UserEntity user = userRepository.findByUsername(username).orElseThrow();
		BasketEntity basket = basketRepository.findByUserIdAndCarId(user.getId(),carId).orElse(new BasketEntity());
		basket.setUserId(user.getId());
		basket.setCarId(carId);
		if(basket.getQuantity()==null) {
			basket.setQuantity(1);
		}else {
			basket.setQuantity(basket.getQuantity()+1);
		}
		basketRepository.save(basket);
	}
	public List<BasketEntity> getMyBasket() {
	    String username = SecurityUtil.getCurrentUsername();
	    UserEntity user = userRepository.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
	    return basketRepository.findByUserId(user.getId());
	}

}
