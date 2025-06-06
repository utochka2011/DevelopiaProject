package com.example.developia.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clothes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClothesRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank(message = "Название бренда не должно быть пустым")
    @Size(min=3,max = 50, message = "Название бренда не должно превышать 50 символов и не должно быть ниже 3 символов")
    private String brand;
    
    @NotBlank(message = "Название модели не должно быть пустым")
    @Size(max = 50, message = "Название модели не должно превышать 50 символов")
    private String model;
    
    @NotBlank(message = "Описание не должно быть пустым")
    @Size(max = 1000, message = "Описание не должно превышать 1000 символов")
    private String description;
    
    @NotNull(message = "Цена товара не должна быть пустой")
    private Double price;
    
    @NotNull(message = "Размер не должен быть пустым")
    @Size(max = 10, message = "Размер не должен превышать 10 символов")
    private String size;
    @JsonProperty("imageUrl")
    private String imgUrl;
    
    @NotNull(message = "Рейтинг не должен быть пустым")
    @Min(value = 1, message = "Рейтинг должен быть не меньше 1")
    @Max(value = 5, message = "Рейтинг должен быть не больше 5")
    private Integer rating;
   
}
