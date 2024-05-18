package com.example.food_basket_optimization.extraction.properties;

import com.example.food_basket_optimization.extraction.ExtractedEntity;

import java.util.List;
// todo: make abstract Property
public interface Property<T> {
    List<ExtractedEntity> getReferenceEntities();
    T property();
}
