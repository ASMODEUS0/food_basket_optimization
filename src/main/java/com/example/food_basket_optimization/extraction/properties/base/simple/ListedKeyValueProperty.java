package com.example.food_basket_optimization.extraction.properties.base.simple;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueProperty;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class ListedKeyValueProperty implements Property<List<KeyValueProperty>>{

    private final List<KeyValueProperty> property;

    @Override
    public List<ExtractedEntity> getReferenceEntities() {
        return property.stream()
                .map(KeyValueProperty::getReferenceEntities)
                .flatMap(Collection::stream)
                .toList();
    }

    @Override
    public List<KeyValueProperty> property() {
        return property;
    }
}


