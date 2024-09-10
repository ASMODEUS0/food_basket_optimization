package com.example.food_basket_optimization.extraction.properties.base.simple;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.SimplePropertyAbs;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class StringProperty extends SimplePropertyAbs<String> {

    public static Constructor<StringProperty> getListedConstructor() {
        try {
            return StringProperty.class.getConstructor(List.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private final String property;

    private final List<ExtractedEntity> referenceEntities;

    public StringProperty(java.lang.String property, List<ExtractedEntity> referenceEntities) {
        this.property = property;
        this.referenceEntities = referenceEntities;
    }

    public StringProperty(List<StringProperty> properties) {
        referenceEntities = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        properties.forEach(property -> {
                    referenceEntities.addAll(property.getReferenceEntities());
                    sb.append(property);
                }
        );
        property = sb.toString();
    }

    public StringProperty(java.lang.String property) {
        this.property = property;
        this.referenceEntities = new ArrayList<>();
    }

    @Override
    public List<ExtractedEntity> getReferenceEntities() {
        return referenceEntities;
    }

    @Override
    public String property() {
        return property;
    }

    @Override
    public String toString() {
        return property;
    }
}
