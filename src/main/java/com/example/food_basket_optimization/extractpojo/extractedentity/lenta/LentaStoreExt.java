package com.example.food_basket_optimization.extractpojo.extractedentity.lenta;

import com.example.food_basket_optimization.entity.ShopType;
import com.example.food_basket_optimization.entity.Store;
import com.example.food_basket_optimization.extraction.ExtractedEntityMappedObject;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.annotation.JsonCollection;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonCollection
@Setter
@Getter
public class LentaStoreExt implements ExtractedEntityMappedObject<Store> {

    public String id;
    public String name;
    public String address;
    public String cityKey;
    public String cityName;
    public String type;
    public double lat;
    @JsonProperty("long")
    public double mylong;
    public String opensAt;
    public String closesAt;
    public boolean isDefaultStore;
    public boolean isEcomAvailable;
    public boolean isPickupAvailable;
    public boolean isDeliveryAvailable;
    public boolean isLentaScanAvailable;
    public boolean is24hStore;
    public boolean hasPetShop;
    public boolean hasTobaccoPoint;
    public String division;
    public boolean isFavorite;
    public int minOrderSumm;
    public Object maxOrderSumm;
    public int minDeliveryOrderSumm;
    public Object maxDeliveryOrderSumm;
    public int maxWeight;
    public int maxDeliveryWeight;
    public int maxQuantityPerItem;
    public int maxDeliveryQuantityPerItem;
    public int orderLimitOverall;
    public int deliveryOrderLimitOverall;
    public String storeTimeZoneOffset;
    public String url;
    public boolean showOnTheMap;


    @Override
    public Store map(Object... args) {
        return Store.builder()
                .address(address)
                .longitude(mylong)
                .latitude(lat)
                .shopType(ShopType.LENTA)
                .build();
    }
}
