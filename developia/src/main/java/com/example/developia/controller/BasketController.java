package com.example.developia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.developia.request.BasketResponse;
import com.example.developia.service.BasketService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/basket")
public class BasketController {
    @Autowired
    private BasketService basketService;
    
    @PostMapping("/add/{clothesId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void addToBasket(@PathVariable Long clothesId) {
        basketService.addToBasket(clothesId);
    }
    
    @DeleteMapping("/remove/{clothesId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void removeFromBasket(@PathVariable Long clothesId) {
        basketService.removeFromBasket(clothesId);
    }
    
    @GetMapping("/my-basket")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BasketResponse> getMyBasket() {
        return basketService.getMyBasket();
    }
}