package com.example.food_basket_optimization.importer.parser.parsedproperties.url;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class KeyValueUrlBasicContextual implements KeyValueUrlProperty{

    private final String key;
    private final String value;
    @Getter
    private final Object referenceEntity;

    public KeyValueUrlBasicContextual(String key, String value, Object referenceEntities ) {
        this.key = key;
        this.value = value;
        this.referenceEntity = referenceEntities;
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
