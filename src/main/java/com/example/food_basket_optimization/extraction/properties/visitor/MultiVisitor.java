package com.example.food_basket_optimization.extraction.properties.visitor;

import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.constructableobject.ConstructableNode;
import com.example.food_basket_optimization.extraction.properties.base.multi.MultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.base.postmulti.PostMultiplyingProperty;

public interface MultiVisitor {

    void property(SimpleProperty<?> resolvableProperty);

    void multi(MultiplyingProperty<?> resolvableProperty);

    void complex(ConstructableNode<?> resolvableProperty);

    void post(PostMultiplyingProperty<?> resolvableProperty);

    PropertyContainer getPropertyContainer();

}
