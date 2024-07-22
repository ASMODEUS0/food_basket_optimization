package com.example.food_basket_optimization.extractpojo.extractedentity.magnit;

import com.example.food_basket_optimization.entity.ShopType;
import com.example.food_basket_optimization.entity.Store;
import com.example.food_basket_optimization.extraction.ExtractedEntityMappedObject;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.annotation.JsonCollection;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("stores")
@JsonCollection
public class MagnitStoreExt implements ExtractedEntityMappedObject<Store> {
    public String address;
    public String code;
    public String id;
    public boolean isPricecheckerAvailable;
    public double latitude;
    public double longitude;
    public String modification;
    public String name;
    public String openingHours;
    public String closingHours;
    public String type;
    public String city;
    public String cityFiasId;

    @Override
    public Store map(Object... args) {
        return Store.builder()
                .shopType(ShopType.MAGNIT)
                .longitude(longitude)
                .latitude(latitude)
                .address(address)
                .build();
    }
}
