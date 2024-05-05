package com.example.food_basket_optimization.extraction.properties.base.simple;

import com.example.food_basket_optimization.extraction.ExtractedEntity;

import java.util.ArrayList;
import java.util.List;

public class StringProperty implements SimpleString {

    private final String property;

    private  final List< ExtractedEntity> referenceEntities;

    public StringProperty(java.lang.String property, List< ExtractedEntity> referenceEntities) {
        this.property = property;
        this.referenceEntities = referenceEntities;
    }

    public StringProperty(java.lang.String property) {
        this.property = property;
        this.referenceEntities = new ArrayList<>();
    }

    @Override
    public List< ExtractedEntity> getReferenceEntities() {
        return referenceEntities;
    }

    @Override
    public String property() {
        return property;
    }
}
