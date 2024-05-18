package com.example.food_basket_optimization.extraction.properties.propertyconstructor;

import com.example.food_basket_optimization.extraction.properties.Property;

public interface Constructor {
    Property<?> construct(Property<?>... properties);

}
