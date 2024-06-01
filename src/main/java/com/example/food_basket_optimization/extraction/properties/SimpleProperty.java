package com.example.food_basket_optimization.extraction.properties;

import com.example.food_basket_optimization.extraction.ExtractedEntity;

import java.util.List;

public interface SimpleProperty<T> extends Property{
    List<ExtractedEntity> getReferenceEntities();
    T property();
}
