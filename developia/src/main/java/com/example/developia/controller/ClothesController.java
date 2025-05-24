package com.example.developia.controller;

import com.example.developia.entity.ClothesEntity;
import com.example.developia.request.ClothesRequest;
import com.example.developia.service.ClothesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins={"http://127.0.0.1:5500", "http://localhost:5500"}, allowCredentials = "true")
@RestController
@RequestMapping("/clothes")
public class ClothesController {

    @Autowired
    private ClothesService clothesService;

    @GetMapping
    public List<ClothesEntity> getAllClothes() {
        return clothesService.getAllClothes();
    }

    @GetMapping("/my-clothes")
    public List<ClothesEntity> getMyClothes() {
        return clothesService.getMyClothes();
    }

    @GetMapping("/{id}")
    public Optional<ClothesEntity> getClothes(@PathVariable Long id) {
        return clothesService.getClothesById(id);
    }

    @GetMapping("/search")
    public List<ClothesEntity> searchClothes(@RequestParam String keyword) {
        return clothesService.searchClothes(keyword);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void addClothes(@RequestBody ClothesRequest clothesRequest) {
        clothesService.addClothesForCurrentUser(clothesRequest);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteClothes(@PathVariable Long id) {
        clothesService.deleteClothes(id);
    }

    @GetMapping("/filter-by-rating")
    public List<ClothesEntity> filterByMinRating(@RequestParam Integer minRating) {
        return clothesService.filterByMinRating(minRating);
    }

    @GetMapping("/sort-by-price-asc")
    public List<ClothesEntity> sortByPriceAsc() {
        return clothesService.sortByPriceAsc();
    }

    @GetMapping("/sort-by-price-desc")
    public List<ClothesEntity> sortByPriceDesc() {
        return clothesService.sortByPriceDesc();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void updateClothes(@PathVariable Long id, @RequestBody ClothesRequest clothesRequest) {
        clothesService.updateClothes(id, clothesRequest);
    }
}
