package com.example.food_basket_optimization.extraction.properties.base.keyvalue.basic;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValue;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueProperty;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for extends KeyValueUrlProperties, classes that extends this
 * class
 */
@EqualsAndHashCode
public class KeyValueBasicProperty implements KeyValueProperty {

    private final String key;
    private final String value;
    private final List<ExtractedEntity> referenceEntities;

    public KeyValueBasicProperty(String key, String value) {
        this.key = key;
        this.value = value;
        this.referenceEntities = new ArrayList<>();
    }


    public KeyValueBasicProperty(String key, String value, List< ExtractedEntity> referenceEntities) {
        this.key = key;
        this.value = value;
        this.referenceEntities = referenceEntities;
    }


    public void addRefEntity(ExtractedEntity entity){
        referenceEntities.add(entity);
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
    public KeyValue property() {
        return new KeyValue(key, value);
    }
}
