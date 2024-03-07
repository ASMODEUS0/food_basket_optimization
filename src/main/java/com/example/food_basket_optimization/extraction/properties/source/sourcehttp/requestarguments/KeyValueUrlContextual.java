package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extraction.properties.util.ExtractUtil;
import com.example.food_basket_optimization.extraction.properties.util.RefValue;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * A property that is used to describe a url param or header with the ability to multiply based on context
 */
public class KeyValueUrlContextual implements KeyValueUrlProperty, ReferencedExtraction {


    private final String value;
    private final String key;
    private final ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext;

    @Getter
    private final RefValue refValue;


    public KeyValueUrlContextual(String key,
                                 String value,
                                 ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext,
                                 RefValue refValue) {
        this.key = key;
        this.value = value;
        this.extractContext = extractContext;
        this.refValue = refValue;
    }


    @Override
    public String key() {
        return key;
    }

    @Override
    public String value() {
        return value;
    }


    @Override
    public List<KeyValueUrlBasicContextual> multiply() {
        ArrayList<KeyValueUrlBasicContextual> result = new ArrayList<>();

        Map<? extends ExtractedEntity, Object> objectFieldValueMap = ExtractUtil.getValueFromField(refValue.getRefClass(), refValue.getFieldName(), extractContext);

        objectFieldValueMap.forEach((object, fieldValue) ->{
          result.add(new KeyValueUrlBasicContextual(this.key, this.value + (String) fieldValue, object)) ;
        });
        return result;
    }

    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return List.of(refValue.refClass);
    }
}
