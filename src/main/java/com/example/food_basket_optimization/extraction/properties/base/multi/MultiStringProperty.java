package com.example.food_basket_optimization.extraction.properties.base.multi;

import com.example.food_basket_optimization.extraction.properties.base.simple.SimpleString;
import com.example.food_basket_optimization.extraction.properties.base.simple.StringProperty;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class MultiStringProperty implements MultiString {


    private final String property;

    @Override
    public List<SimpleString> multiply() {
        return List.of(new StringProperty(property));
    }
}
