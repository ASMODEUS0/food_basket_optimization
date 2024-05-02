package com.example.food_basket_optimization.extractpojo;

public interface MappingEntity<T> {
    T map(Object ... entities);
}
