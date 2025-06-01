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
    private String brand;
    private String model;
    private String description;
    private Double price;
    private String size;
    private String imgUrl;
    private Integer rating;
    private Long ownerId;
    private Integer quantity;
}
