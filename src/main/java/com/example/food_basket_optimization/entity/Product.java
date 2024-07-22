package com.example.food_basket_optimization.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer stock;
    private String brand;
    private String title;
    private String subTitle;
    private String imageUrl;
    private Double price;
    @Enumerated(EnumType.STRING)
    private ShopType shopType;


}
