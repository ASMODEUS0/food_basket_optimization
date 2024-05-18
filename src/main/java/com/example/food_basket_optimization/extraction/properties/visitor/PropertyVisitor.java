package com.example.food_basket_optimization.extraction.properties.visitor;

import com.example.food_basket_optimization.extraction.properties.ResolvableProperty;
import com.example.food_basket_optimization.extraction.properties.SimpleResolvableProperty;
import com.example.food_basket_optimization.extraction.properties.base.prepostmulti.ComplexMultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.util.MultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.util.PostMultiplyingProperty;

import java.util.ArrayList;

public class PropertyVisitor implements MultiVisitor{
    private final PropertyContainerConstructor propertyContainer;

    public PropertyVisitor(PropertyContainerConstructor propertyContainer){
        this.propertyContainer = propertyContainer;
    }


    @Override
    public void simple(SimpleResolvableProperty<?> resProperty, ResolvableProperty fatherProperty) {

    }

    @Override
    public void multi(MultiplyingProperty<?> resProperty) {
       propertyContainer.addResolvedProperty(new ArrayList<>(resProperty.multiply()));
    }


    @Override
    public void post(PostMultiplyingProperty<?> resProperty) {

    }

    @Override
    public void complex(ComplexMultiplyingProperty<?> resProperty) {

    }



}