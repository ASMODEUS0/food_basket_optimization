package com.example.food_basket_optimization.extractedentity.lenta;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LentaStoreExt implements ExtractedEntity {
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


}
