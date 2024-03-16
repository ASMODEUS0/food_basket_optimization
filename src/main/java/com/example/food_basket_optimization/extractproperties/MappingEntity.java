package com.example.food_basket_optimization.extractproperties;

public interface MappingEntity<T> {
    T map(Object ... entities);
}
