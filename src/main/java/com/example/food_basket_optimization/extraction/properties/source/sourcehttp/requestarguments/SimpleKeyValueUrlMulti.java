package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.base.keyvalue.basic.KeyValueBasicProperty;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiVisitor;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class SimpleKeyValueUrlMulti implements KeyValueUrlMultiProperties {

    private final String key;
    private final String value;
    @Override
    public List<KeyValueProperty> multiply() {
        return List.of(new KeyValueBasicProperty(key, value));
    }


    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return List.of();
    }

    @Override
    public void visit(MultiVisitor visitor) {

    }
}
