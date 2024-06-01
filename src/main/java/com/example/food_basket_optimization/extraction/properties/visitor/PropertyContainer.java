package com.example.food_basket_optimization.extraction.properties.visitor;

import com.example.food_basket_optimization.extraction.properties.propertyconstructor.PropertySource;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.ReferenceObject;

import java.util.List;

public interface PropertyContainer extends ReferenceObject {
    List<PropertySource> getSources();
}
