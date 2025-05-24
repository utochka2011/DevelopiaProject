package com.example.developia.request;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String brand;
    private String model;
    private String description;
    private Double price;
    private String size;
    private String imgUrl;
    private Integer rating;
}
