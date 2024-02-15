package com.example.food_basket_optimization.importer.parser.parsedproperties.url;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for extends KeyValueUrlProperties, classes that extends this
 * class
 */
public class KeyValueUrlBasic implements KeyValueUrlProperty {

    private final String key;
    private final String value;

    public KeyValueUrlBasic(String key, String value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public List<KeyValueUrlProperty> multiply() {
        ArrayList<KeyValueUrlProperty> result = new ArrayList<>();
        result.add(this);
        return result;
    }

}
