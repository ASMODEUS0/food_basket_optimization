package com.example.food_basket_optimization.extraction.properties.base.simple;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValue;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueProperty;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiVisitor;
import com.example.food_basket_optimization.extraction.properties.visitor.ReferenceVisitor;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class for extends KeyValueUrlProperties, classes that extends this
 * class
 */
@EqualsAndHashCode
public class KeyValueSimpleProperty implements KeyValueProperty {

    private final String key;
    private final String value;
    private final List<ExtractedEntity> referenceEntities;

    public static Constructor<KeyValueSimpleProperty> getConstructor(){
        try {
            return KeyValueSimpleProperty.class.getConstructor(SimpleProperty.class, SimpleProperty.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public KeyValueSimpleProperty(String key, String value) {
        this.key = key;
        this.value = value;
        this.referenceEntities = new ArrayList<>();
    }


    public KeyValueSimpleProperty(String key, String value, List< ExtractedEntity> referenceEntities) {
        this.key = key;
        this.value = value;
        this.referenceEntities = referenceEntities;
    }

    public KeyValueSimpleProperty(SimpleProperty<String> key, SimpleProperty<String> value){
        referenceEntities = new ArrayList<>();
        this.key = key.property();
        this.value = value.property();
        referenceEntities.addAll(key.getReferenceEntities());
        referenceEntities.addAll(value.getReferenceEntities());
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

    @Override
    public void visit(MultiVisitor visitor) {
        visitor.property(this);
    }

    @Override
    public void visit(ReferenceVisitor visitor) {
        visitor.visit(this);
    }
}
