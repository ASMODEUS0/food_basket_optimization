package com.example.food_basket_optimization.importer.parser.parsedproperties.url;

import java.util.ArrayList;
import java.util.List;


public class KeyValueUrlIterable implements KeyValueUrlProperty {

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
     *
     */
    @Override
    public List<KeyValueUrlProperty> multiply() {
        List<KeyValueUrlProperty> result = new ArrayList<>();
        for(int i = startValue; i <= endValue; i++){
            result.add(new KeyValueUrlBasic(getKey(), getValue() + i));
        }
        return result;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }
}
