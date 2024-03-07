package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.util.ReferencedProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;


/**
 *
 */
@RequiredArgsConstructor
public class KeyValueUrlBasicContextual implements KeyValueUrlBasicProperty , ReferencedProperty {
    private final String key;
    private final String value;
    @Getter
    private final ExtractedEntity referenceEntity;



    @Override
    public String key() {
        return key;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public List<? extends ExtractedEntity> getRefProperties() {
        return List.of(referenceEntity);
    }
}
