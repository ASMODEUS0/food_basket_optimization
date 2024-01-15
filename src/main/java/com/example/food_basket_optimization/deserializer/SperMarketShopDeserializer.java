package com.example.food_basket_optimization.deserializer;

import com.example.food_basket_optimization.pojo.CityPojo;
import com.example.food_basket_optimization.pojo.LocationPojo;
import com.example.food_basket_optimization.pojo.ShopPOJO;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class SperMarketShopDeserializer extends JsonDeserializer<ShopPOJO> {


    public SperMarketShopDeserializer(){

    }


    @Override
    public ShopPOJO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode shopNode = p.readValueAsTree();
        JsonNode innerNode = shopNode.get("store");
        CityPojo city = CityPojo.builder()
                .nameOfCity(innerNode.get("location").get("city").asText())
                .build();

        LocationPojo location = LocationPojo.builder()
                .lat(innerNode.get("location").get("lat").asDouble())
                .lon(innerNode.get("location").get("lon").asDouble())
                .build();


        ShopPOJO shop = ShopPOJO.builder()
                .address(innerNode.get("location").get("street").asText())
                .location(location)
                .city(city)
                .retailerName(innerNode.get("retailer").get("name").asText())
                .build();

        return shop;
    }

}
