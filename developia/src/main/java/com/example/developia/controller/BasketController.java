package com.example.developia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.developia.entity.BasketEntity;
import com.example.developia.request.BasketResponse;
import com.example.developia.service.BasketService;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/basket")
public class BasketController {
	@Autowired
	private BasketService basketService;

	@PostMapping("/add/{carId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public void addToBasket(@PathVariable Long carId) {
		basketService.addToBasket(carId);
	}

	@DeleteMapping("/remove/{carId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public void removeFromBasket(@PathVariable Long carId) {
		basketService.removeFromBasket(carId);
	}

	@GetMapping("/my-basket")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<BasketResponse> getMyBasket() {
		return basketService.getMyBasket();
	}
}