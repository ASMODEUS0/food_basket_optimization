package com.example.food_basket_optimization.extractedentity;

public interface MappingEntity<T> {
    T map(Object ... entities);
}
