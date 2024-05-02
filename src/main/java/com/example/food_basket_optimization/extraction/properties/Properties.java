package com.example.food_basket_optimization.extraction.properties;

import com.example.food_basket_optimization.extraction.ExtractedEntity;

import java.util.List;

public interface Properties<T> {
    List<ExtractedEntity> getReferenceEntities();
    T getProperty();
}
