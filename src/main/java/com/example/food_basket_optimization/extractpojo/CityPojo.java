package com.example.food_basket_optimization.extractpojo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CityPojo {

    private Long id;
    private String region;
    private String regionType;
    private String nameOfCity;
    private Double latitude;
    private Double longitude;
    private String timeZone;
}
