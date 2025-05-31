package com.example.developia.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasketResponse {
    private Long id;
    
    @NotBlank(message = "Название бренда не должно быть пустым")
    @Size(max = 50, message = "Название бренда не должно превышать 50 символов")
    private String brand;
    
    @NotBlank(message = "Название модели не должно быть пустым")
    @Size(max = 50, message = "Название модели не должно превышать 50 символов")
    private String model;
    
    @NotBlank(message = "Описание не должно быть пустым")
    @Size(max = 1000, message = "Описание не должно превышать 1000 символов")
    private String description;
    
    @NotNull(message = "Цена товара не должна быть пустой")
    private Double price;
    
    @NotBlank(message = "Размер не должен быть пустым")
    @Size(max = 10, message = "Размер не должен превышать 10 символов")
    private String size;
    
    private String imgUrl;
    
    @NotNull(message = "Рейтинг не должен быть пустым")
    @Min(value = 1, message = "Рейтинг должен быть не меньше 1")
    @Max(value = 5, message = "Рейтинг должен быть не больше 5")
    private Integer rating;
    
    private Long ownerId;
    
    @NotNull(message = "Количество не должно быть пустым")
    @Min(value = 1, message = "Количество должно быть минимум 1")
    private Integer quantity;
}