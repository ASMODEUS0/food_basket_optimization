package com.example.food_basket_optimization.extractpojo.extractedentity.diksi;

import com.example.food_basket_optimization.extraction.ExtractedEntityMappedObject;
import lombok.Builder;

@Builder
public class DiksiShopExt implements ExtractedEntityMappedObject {

    private double longitude;
    private double latitude;
    private String adress;
    private String openingHours;


    @Override
    public Object map(Object... args) {
        return null;
    }
}
