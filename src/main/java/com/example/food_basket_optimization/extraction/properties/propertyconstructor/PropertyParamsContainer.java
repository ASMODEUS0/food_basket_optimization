package com.example.food_basket_optimization.extraction.properties.propertyconstructor;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PropertyParamsContainer implements ReferenceObject {

    private final List<SimpleProperty<?>> resolvedParams;

    public PropertyParamsContainer(List<SimpleProperty<?>> resolvedParams) {
        this.resolvedParams = resolvedParams;
    }


    public PropertyParamsContainer doCopyWithSetParam(Integer index, SimpleProperty<?> param){
        List<SimpleProperty<?>> copy = new ArrayList<>(resolvedParams);
        copy.set(index, param);
        return new PropertyParamsContainer(copy);
    }

    @Override
    public List<ExtractedEntity> getRefEntities() {
        return resolvedParams.stream()
                .map(SimpleProperty::getReferenceEntities)
                .flatMap(Collection::stream)
                .toList();
    }

    public List<SimpleProperty<?>> toList(){
        return resolvedParams;
    }
}
