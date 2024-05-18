package com.example.food_basket_optimization.extraction.properties.base.prepostmulti;

import com.example.food_basket_optimization.extraction.properties.ResolvableProperty;
import com.example.food_basket_optimization.extraction.properties.base.multi.Multiplying;
import com.example.food_basket_optimization.extraction.properties.util.MultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.util.PostMultiplyingProperty;

public interface ComplexMultiplyingProperty<T extends PostMultiplyingProperty<?>> extends Multiplying<T>, ResolvableProperty {
}
