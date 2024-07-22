package com.example.food_basket_optimization.extraction.properties.base.simple;

import com.example.food_basket_optimization.dto.ProductDto;
import com.example.food_basket_optimization.extraction.properties.SimplePropertyAbs;
import com.example.food_basket_optimization.extractpojo.LocationPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonObjectProperty extends SimplePropertyAbs<String> {


    public static void main(String[] args) {
        JsonObjectProperty property = new JsonObjectProperty(new LocationPojo(10.1, 10.2));
        String property1 = property.property();

        System.out.println("");
    }

    private final Object object;

    public JsonObjectProperty(Object object) {
        this.object = object;
    }

    @Override
    public String property() {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
