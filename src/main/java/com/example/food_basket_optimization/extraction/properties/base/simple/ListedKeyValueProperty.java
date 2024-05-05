package com.example.food_basket_optimization.extraction.properties.base.simple;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueUrlProperty;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class ListedKeyValueProperty implements Property<List<KeyValueUrlProperty>>{

    private final List<KeyValueUrlProperty> property;

    @Override
    public List<ExtractedEntity> getReferenceEntities() {
        return property.stream()
                .map(KeyValueUrlProperty::getReferenceEntities)
                .flatMap(Collection::stream)
                .toList();
    }

    @Override
    public List<KeyValueUrlProperty> property() {
        return property;
    }
}


