package com.example.food_basket_optimization.extraction.properties.propertyconstructor;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.propertyconstructor.PropertyConstructor;
import com.example.food_basket_optimization.extraction.properties.base.postmulti.PostMultiplyingProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
public class PropertySource implements ReferenceObject{

    private final List<SimpleProperty<?>> resolvedProperties;
    private final Map<Integer, PropertyConstructor<?>> constructedResolvedProperties;
    private final Map<Integer, PostMultiplyingProperty<?>> postResolvedProperties;


    public PropertySource(List<SimpleProperty<?>> resolvedProperties,
                          Map<Integer, PostMultiplyingProperty<?>> postResolvedProperties,
                          Map<Integer, PropertyConstructor<?>> constructedResolvedProperties
    ) {
        this.constructedResolvedProperties = constructedResolvedProperties;
        this.resolvedProperties = resolvedProperties;
        this.postResolvedProperties = postResolvedProperties;
    }

    @Override
    public List<ExtractedEntity> getRefEntities() {
        ArrayList<ExtractedEntity> refEntities = new ArrayList<>();
        constructedResolvedProperties.forEach((num, property)-> refEntities.addAll(property.getRefEntities()));
        refEntities.addAll(resolvedProperties.stream().map(SimpleProperty::getReferenceEntities).flatMap(Collection::stream).toList());
        return refEntities;
    }
}
