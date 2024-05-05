package com.example.food_basket_optimization.extraction.properties.util;

import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.base.multi.Multiplying;

public interface MultiplyingProperty<T extends Property<?>> extends Multiplying<T>, ReferencedExtraction {
}
