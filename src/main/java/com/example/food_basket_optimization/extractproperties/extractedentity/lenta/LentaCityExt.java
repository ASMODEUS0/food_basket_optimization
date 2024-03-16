package com.example.food_basket_optimization.extractproperties.extractedentity.lenta;

import com.example.food_basket_optimization.entity.City;
import com.example.food_basket_optimization.extraction.ExtractedEntityMappedObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@JsonIgnoreProperties(value = {""})
public class LentaCityExt implements ExtractedEntityMappedObject<City> {
    public String id;
    public String name;
    public double lat;
    @JsonProperty("long")
    public double mylong;
    public boolean mediumStoreConcentration;
    public boolean highStoreConcentration;
    public String deliveryOptionPopupDefaultValue;

    public LentaCityExt(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public City map(Object... args) {
        return City.builder()
                .nameOfCity(name)
                .latitude(lat)
                .longitude(mylong)
                .build();
    }
}