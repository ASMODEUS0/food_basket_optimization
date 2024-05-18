package com.example.food_basket_optimization.extraction.properties.visitor;

import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.util.PostMultiplyingProperty;

import java.util.List;

public interface PropertyContainerConstructor {
     void addResolvedProperty(List<Property<?>> property);

     void addPostProperties(PostMultiplyingProperty<?> postResolvedProperty);

}
