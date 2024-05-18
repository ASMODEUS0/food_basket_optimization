package com.example.food_basket_optimization.extraction.properties.util;

import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.ResolvableProperty;
import com.example.food_basket_optimization.extraction.properties.base.multi.Multiplying;
import com.example.food_basket_optimization.extraction.properties.visitor.Multi;

public interface MultiplyingProperty<T extends Property<?>> extends Multiplying<T>, ReferencedExtraction, ResolvableProperty {
}
