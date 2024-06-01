package com.example.food_basket_optimization.extraction.properties.base.simple;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.SimplePropertyAbs;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueProperty;

import lombok.RequiredArgsConstructor;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class ListedKeyValueProperty extends SimplePropertyAbs<List<KeyValueProperty>> {

    private final List<KeyValueProperty> property;

    public static Constructor<ListedKeyValueProperty> getConstructor() {
        try {
            return ListedKeyValueProperty.class.getConstructor(List.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }


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


