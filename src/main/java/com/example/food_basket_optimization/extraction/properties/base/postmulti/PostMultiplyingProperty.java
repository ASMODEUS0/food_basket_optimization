package com.example.food_basket_optimization.extraction.properties.base.postmulti;

import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;

public interface PostMultiplyingProperty<T extends SimpleProperty<?>> extends PostMultiplying<T>, ReferencedExtraction, Property {

}
