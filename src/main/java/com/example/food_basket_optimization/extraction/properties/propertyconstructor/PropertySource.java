package com.example.food_basket_optimization.extraction.properties.propertyconstructor;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.propertyconstructor.PropertyConstructor;
import com.example.food_basket_optimization.extraction.properties.base.postmulti.PostMultiplyingProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public record PropertySource(List<SimpleProperty<?>> resolvedProperties,
                             Map<Integer, PostMultiplyingProperty<?>> postResolvedProperties,
                             Map<Integer, PropertyConstructor<?>> constructedResolvedProperties) implements ReferenceObject {

    @Override
    public List<ExtractedEntity> getRefEntities() {
        return new ArrayList<>(resolvedProperties.stream()
                .map(SimpleProperty::getReferenceEntities)
                .flatMap(Collection::stream)
                .toList());
    }
}
