package com.example.food_basket_optimization.extractpojo.extractedentity.magnit;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.annotation.JsonCollection;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("cities")
@JsonCollection
public class MagnitCityExt implements ExtractedEntity {

    public String city;
    public String id;
    public boolean isEcomAvailable;
    public boolean isEcomDeliveryAvailable;
    public boolean isHealthyClubAvailable;
    public boolean isIncreasedRatio;
    public boolean isLoyaltyAvailable;
    public boolean isMagnitMobileAvailable;
    public boolean isPaymentQrAvailable;
    public boolean isPricecheckerAvailable;
    public double latitude;
    public double longitude;
    public String region;
}
