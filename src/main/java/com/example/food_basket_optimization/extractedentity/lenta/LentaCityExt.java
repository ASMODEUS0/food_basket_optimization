package com.example.food_basket_optimization.extractedentity.lenta;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class LentaCityExt implements ExtractedEntity {
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
}
