package com.example.developia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.developia.entity.BasketEntity;
import com.example.developia.service.BasketService;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/basket")
public class BasketController {
	@Autowired
	private BasketService basketService;
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/add/{carId}")
	public void addToBasket(@PathVariable Long carId) {
		basketService.addToBasket(carId);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/my-basket")
	public List<BasketEntity> getMyBasket() {
	    return basketService.getMyBasket();
	}

}