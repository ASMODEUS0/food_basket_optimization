package com.example.food_basket_optimization.dto;

import com.example.food_basket_optimization.entity.ShopType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record StoreDto(String address,
                       Double latitude,
                       Double longitude,
                       ShopType shopType) {
}
