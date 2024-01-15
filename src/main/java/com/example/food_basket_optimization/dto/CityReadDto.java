package com.example.food_basket_optimization.dto;

public record CityReadDto(Long id,
                          String region,
                          String regionType,
                          String nameOfCity,
                          Double latitude,
                          Double longitude,
                          String timeZone) {
}
