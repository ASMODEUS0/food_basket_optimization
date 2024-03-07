package com.example.food_basket_optimization.extraction.properties.mapping;

import com.example.food_basket_optimization.extraction.ExtractedEntity;

import java.util.List;

public interface MapProperty {
    String getData();
    Class<? extends ExtractedEntity> getClassToMap();
    List<? extends ExtractedEntity> getReferenceEntities();

}
