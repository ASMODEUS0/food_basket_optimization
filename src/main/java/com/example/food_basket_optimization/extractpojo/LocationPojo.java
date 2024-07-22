package com.example.food_basket_optimization.extractpojo;


import lombok.Builder;

@Builder
public class LocationPojo {
    public Double lat;
    public Double lon;

    public LocationPojo(Double lat,
                        Double lon){
        this.lat = lat;
        this.lon = lon;
    }

}
