package com.example.food_basket_optimization.extraction.properties.visitor;

import com.example.food_basket_optimization.extraction.properties.SimpleResolvableProperty;
import com.example.food_basket_optimization.extraction.properties.util.MultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.util.PostMultiplyingProperty;

import java.util.ArrayList;
import java.util.List;

public class PropertyComponents {
    private final List<SimpleResolvableProperty<?>> simpleParams;
    private final List<MultiplyingProperty<?>> multiParams;
    private final List<PostMultiplyingProperty<?>> postParam;


    public PropertyComponents(){
        simpleParams = new ArrayList<>();
        multiParams = new ArrayList<>();
        postParam = new ArrayList<>();
    }

}
