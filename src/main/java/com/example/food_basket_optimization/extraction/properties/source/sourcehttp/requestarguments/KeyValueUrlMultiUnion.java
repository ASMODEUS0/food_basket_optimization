package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.util.MultiplierUtil;
import com.example.food_basket_optimization.extraction.properties.util.Multiplying;

import java.util.Collection;
import java.util.List;

public class KeyValueUrlMultiUnion implements KeyValueUrlMultiUnionProperties {

    private final List<KeyValueUrlMultiProperties> properties;

    public KeyValueUrlMultiUnion(List<KeyValueUrlMultiProperties> properties) {
        this.properties = properties;

    }

    @Override
    public List<List<KeyValueUrlProperties>> multiply() {
        List<List<KeyValueUrlProperties>> multipliedKeyValues = properties.stream()
                .map(Multiplying::multiply)
                .toList();
        return MultiplierUtil.resolveListedMultiplication(multipliedKeyValues);
    }


    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return properties.stream().map(KeyValueUrlMultiProperties::getRefClasses)
                .flatMap(Collection::stream)
                .toList();
    }
}
