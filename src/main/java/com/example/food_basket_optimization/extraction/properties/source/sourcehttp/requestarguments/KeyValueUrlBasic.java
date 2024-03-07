package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments;

import lombok.EqualsAndHashCode;

/**
 * Base class for extends KeyValueUrlProperties, classes that extends this
 * class
 */
@EqualsAndHashCode
public class KeyValueUrlBasic implements KeyValueUrlBasicProperty {

    private final String key;
    private final String value;

    public KeyValueUrlBasic(String key, String value) {
        this.key = key;
        this.value = value;
    }
    @Override
    public String key() {
        return key;
    }

    @Override
    public String value() {
        return value;
    }



}
