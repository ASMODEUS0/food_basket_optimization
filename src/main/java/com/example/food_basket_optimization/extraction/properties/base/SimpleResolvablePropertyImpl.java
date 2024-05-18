package com.example.food_basket_optimization.extraction.properties.base;

import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.SimpleResolvableProperty;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiVisitor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleResolvablePropertyImpl<T extends Property<?>> implements SimpleResolvableProperty<T> {
    private final T property;
    @Override
    public T getProperty() {
        return property;
    }

    @Override
    public void visit(MultiVisitor visitor) {
         visitor.simple(this, this);
    }
}
