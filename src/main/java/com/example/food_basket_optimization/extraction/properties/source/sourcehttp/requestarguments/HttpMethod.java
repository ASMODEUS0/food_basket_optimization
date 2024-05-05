package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.Property;
import lombok.Getter;

import java.util.List;

@Getter
public enum HttpMethod implements Property<String> {

    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private final String methodName;

    HttpMethod(String methodName){
      this.methodName = methodName;
    }

    @Override
    public List<ExtractedEntity> getReferenceEntities() {
        return List.of();
    }

    @Override
    public String property() {
        return methodName;
    }
}
