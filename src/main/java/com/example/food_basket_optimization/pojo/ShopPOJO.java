package com.example.food_basket_optimization.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ShopPOJO {

    private String address;
    private String retailerName;
    private CityPojo city;
    private LocationPojo location;

}
