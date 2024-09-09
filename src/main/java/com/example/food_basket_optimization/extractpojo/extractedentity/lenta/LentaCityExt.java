package com.example.food_basket_optimization.extractpojo.extractedentity.lenta;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.annotation.JsonCollection;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName("result")
@JsonCollection
public class LentaCityExt implements ExtractedEntity, ReferencedExtractedEntity {
    public int id;
    public String alias;
    public String title;
    public String marketType;
    public String localTimeZone;
    public String localTimeZoneOffset;
    public String storeOpen;
    public String storeClose;
    public String pickupWindowOpen;
    public String pickupWindowClose;
    public int regionId;
    public String addressShort;
    public String addressFull;
    public double longitude;
    public double latitude;
    public boolean pickupEnable;
    public int enable;
    public int offlineEnable;
    public boolean is24h;
    @Override
    public String toString() {
        return addressShort;
    }

    @Override
    public boolean referencesIsEqual(List<ExtractedEntity> references) {
        return Boolean.TRUE;
    }
}