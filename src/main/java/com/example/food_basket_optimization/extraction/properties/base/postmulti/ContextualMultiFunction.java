package com.example.food_basket_optimization.extraction.properties.base.postmulti;

import com.example.food_basket_optimization.extraction.ExtractedEntity;

import java.util.List;

@FunctionalInterface
public interface ContextualMultiFunction<T> {
    List<T> multiply(List<ExtractedEntity> contextEntities, List<ExtractedEntity> refEntities);
}
