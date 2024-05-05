package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.base.simple.ListedKeyValueProperty;
import com.example.food_basket_optimization.extraction.properties.util.MultiplierUtil;
import com.example.food_basket_optimization.extraction.properties.base.multi.Multiplying;

import java.util.Collection;
import java.util.List;

public class KeyValueUrlMultiUnion implements KeyValueUrlMultiUnionProperties {

    private final List<KeyValueUrlMultiProperties> properties;

    public KeyValueUrlMultiUnion(List<KeyValueUrlMultiProperties> properties) {
        this.properties = properties;

    }

    @Override
    public List<ListedKeyValueProperty> multiply() {
        List<List<KeyValueUrlProperty>> multipliedKeyValues = properties.stream()
                .map(Multiplying::multiply)
                .toList();
        return MultiplierUtil.directProduct(multipliedKeyValues).stream()
                .map(ListedKeyValueProperty::new).toList();
    }


    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return properties.stream().map(KeyValueUrlMultiProperties::getRefClasses)
                .flatMap(Collection::stream)
                .toList();
    }
}
