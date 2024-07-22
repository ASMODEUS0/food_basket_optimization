package com.example.food_basket_optimization.dto;

import com.example.food_basket_optimization.entity.ShopType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record ProductDto(Integer stock,
                         String brand,
                         String title,
                         String subTitle,
                         String imageUrl,
                         Double price,
                         ShopType shopType) {
}
