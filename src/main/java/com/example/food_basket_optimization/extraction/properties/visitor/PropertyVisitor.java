package com.example.food_basket_optimization.extraction.properties.visitor;

import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.constructableobject.ConstructableNode;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.propertyconstructor.PropertyConstructor;
import com.example.food_basket_optimization.extraction.properties.base.multi.MultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.base.postmulti.PostMultiplyingProperty;

import java.util.ArrayList;
import java.util.List;
/**
 *Class responsible for correctly placing different types of Property in MultiPropertyContainer.
 **/
public class PropertyVisitor implements MultiVisitor {
    private final MultiPropertyContainer propertyContainer;

    public PropertyVisitor() {
        this.propertyContainer = new MultiPropertyContainer();
    }

    @Override
    public void property(SimpleProperty<?> resolvableProperty) {
        propertyContainer.addResolvedParam(List.of(resolvableProperty));
    }


    @Override
    public void multi(MultiplyingProperty<?> resolvableProperty) {
        List<? extends SimpleProperty<?>> resolvedProperties = resolvableProperty.multiply();
        propertyContainer.addResolvedParam(new ArrayList<>(resolvedProperties));
    }

    @Override
    public void complex(ConstructableNode<?> resolvableProperty) {
        List<PropertyConstructor<?>> propertyConstructors = new ArrayList<>(resolvableProperty.constructObject());
        propertyContainer.addConstructableProperty(propertyConstructors);
    }

    @Override
    public void post(PostMultiplyingProperty<?> resolvableProperty) {
        propertyContainer.addPostParam(resolvableProperty);
    }


    @Override
    public PropertyContainer getPropertyContainer() {
        return propertyContainer;
    }
}