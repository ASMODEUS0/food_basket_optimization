package com.example.food_basket_optimization.extraction.properties;

public interface SimpleResolvableProperty<T extends Property<?>> extends ResolvableProperty{
    T getProperty();
}
