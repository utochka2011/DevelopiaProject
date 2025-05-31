package com.example.developia.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "basket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long clothesId;
    private Integer quantity;
}