package com.example.food_basket_optimization.extraction.properties.base.multi;

import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.base.multi.Multiplying;

public interface MultiplyingProperty<T extends SimpleProperty<?>> extends Multiplying<T>, Property {
}
