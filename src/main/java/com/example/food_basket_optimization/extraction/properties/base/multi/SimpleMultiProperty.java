package com.example.food_basket_optimization.extraction.properties.base.multi;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.util.MultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiVisitor;

import java.util.List;
public class SimpleMultiProperty<S, T extends Property<S>> implements MultiplyingProperty<T> {

    private final T property;

    public SimpleMultiProperty(T property){
        this.property = property;
    }

    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return List.of();
    }

    @Override
    public List<T> multiply() {
        return List.of(property);
    }

    @Override
    public void visit(MultiVisitor visitor) {

    }
}
