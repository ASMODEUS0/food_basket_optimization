package com.example.food_basket_optimization.extraction.properties.visitor;

import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.ResolvableProperty;
import com.example.food_basket_optimization.extraction.properties.SimpleResolvableProperty;
import com.example.food_basket_optimization.extraction.properties.base.prepostmulti.ComplexMultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.util.MultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.util.PostMultiplyingProperty;

public interface MultiVisitor {
    void simple(SimpleResolvableProperty<?> resProperty, ResolvableProperty fatherProperty);
     void multi(MultiplyingProperty<?> resProperty);
    void complex(ComplexMultiplyingProperty<?> resProperty);
    void post(PostMultiplyingProperty<?> resProperty);
}
