package com.example.food_basket_optimization.extraction.properties.base.multi;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.util.MultiplyingProperty;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class SimpleMultiProperty<T extends Property<?>> implements MultiplyingProperty<T> {

    private final T property;

    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return List.of();
    }

    @Override
    public List<T> multiply() {
        return List.of(property);
    }
}
