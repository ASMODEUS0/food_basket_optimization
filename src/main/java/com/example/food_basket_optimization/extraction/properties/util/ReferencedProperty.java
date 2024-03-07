package com.example.food_basket_optimization.extraction.properties.util;

import com.example.food_basket_optimization.extraction.ExtractedEntity;

import java.util.List;

public interface ReferencedProperty {
    List<? extends ExtractedEntity> getRefProperties();
}
