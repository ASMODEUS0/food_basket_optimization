package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extraction.properties.util.ExtractUtil;
import com.example.food_basket_optimization.extraction.properties.util.MultiplierUtil;
import com.example.food_basket_optimization.extraction.properties.util.Multiplying;

import java.util.ArrayList;
import java.util.List;

public class KeyValueUrlMultiUnion implements KeyValueUrlMultiUnionProperties, ReferencedExtraction {

    private final List<KeyValueUrlMultiProperties> properties;
    private final List<Class<? extends ExtractedEntity>> refClasses = new ArrayList<>();

    public KeyValueUrlMultiUnion(List<KeyValueUrlMultiProperties> properties) {
        this.properties = properties;
        refClasses.addAll(ExtractUtil.detectObjectReferences(properties));

    }

    @Override
    public List<List<KeyValueUrlProperties>> multiply() {
        List<List<KeyValueUrlProperties>> multipliedKeyValues = properties.stream().map(Multiplying::multiply).toList();
        return MultiplierUtil.resolveListedMultiplication(multipliedKeyValues);
    }


    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return refClasses;
    }
}
