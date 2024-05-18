package com.example.food_basket_optimization.extraction.properties.propertyconstructor.parameter;

import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.ConstructableRootObject;

import java.util.List;
import java.util.stream.Collectors;

public interface ConstructableParameter {
     List<Property<?>> constructParameter();

     void registerRootObject(ConstructableRootObject rootObject);
}
