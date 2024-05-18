package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.base.keyvalue.basic.KeyValueBasicProperty;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiVisitor;

import java.util.ArrayList;
import java.util.List;


public class KeyValueUrlIterable implements KeyValueUrlMultiProperties {

    private final int startValue;
    private final int endValue;
    private final String key;
    private final String value;

    public KeyValueUrlIterable(String key, String value, int startValue, int endValue) {
        this.startValue = startValue;
        this.endValue = endValue;
        this.key = key;
        this.value = value;
    }


    /**
     * Multiply property by iterating it from startValue to endValue
     */
    @Override
    public List<KeyValueProperty> multiply() {
        List<KeyValueProperty> result = new ArrayList<>();
        for (int i = startValue; i <= endValue; i++) {
            result.add(new KeyValueBasicProperty(key, value + i));
        }
        return result;
    }

    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return List.of();
    }

    @Override
    public void visit(MultiVisitor visitor) {

    }
}
