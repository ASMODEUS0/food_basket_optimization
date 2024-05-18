package com.example.food_basket_optimization.extraction.properties.util;

import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.ResolvableProperty;

public interface PostMultiplyingProperty<T extends Property<?>> extends PostMultiplying<T> , ReferencedExtraction, ResolvableProperty {

}
