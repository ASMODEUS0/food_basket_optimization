package com.example.food_basket_optimization.extraction.properties.visitor;

import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.propertyconstructor.PropertyConstructor;
import com.example.food_basket_optimization.extraction.properties.base.postmulti.PostMultiplyingProperty;

import java.util.List;

public interface PropertyContainerConstructor {
    void addResolvedProperty(List<SimpleProperty<?>> property);

    void addPostProperties(PostMultiplyingProperty<?> postResolvedProperty);

    void addConstructableProperty(List<PropertyConstructor<?>> constructableProperty);

}
