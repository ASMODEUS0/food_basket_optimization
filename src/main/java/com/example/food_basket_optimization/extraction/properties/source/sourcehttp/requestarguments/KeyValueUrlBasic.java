package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for extends KeyValueUrlProperties, classes that extends this
 * class
 */
@EqualsAndHashCode
public class KeyValueUrlBasic implements KeyValueUrlProperties {

    private final String key;
    private final String value;
    private final List<ExtractedEntity> referenceEntities;

    public KeyValueUrlBasic(String key, String value) {
        this.key = key;
        this.value = value;
        this.referenceEntities = new ArrayList<>();
    }


    public KeyValueUrlBasic(String key, String value, List< ExtractedEntity> referenceEntities) {
        this.key = key;
        this.value = value;
        this.referenceEntities = referenceEntities;
    }


    @Override
    public String key() {
        return key;
    }

    @Override
    public String value() {
        return value;
    }


    @Override
    public List< ExtractedEntity> getReferenceEntities() {
        return referenceEntities;
    }

    @Override
    public KeyValue getProperty() {
        return new KeyValue(key, value);
    }
}
