package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.util.ExtractUtil;
import com.example.food_basket_optimization.extraction.properties.util.RefValue;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * A property that is used to describe  url param or header with the ability to multiply based on context
 */
public class KeyValueUrlContextual implements KeyValueUrlMultiProperties {


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
    public List<KeyValueUrlProperty> multiply() {
        ArrayList<KeyValueUrlProperty> result = new ArrayList<>();

        Map<? extends ExtractedEntity, Object> objectFieldValueMap = ExtractUtil.getValueFromField(refValue.getRefClass(), refValue.getFieldName(), extractContext);

        objectFieldValueMap.forEach((object, fieldValue) -> result.add(new KeyValueUrlBasic(this.key, this.value +  fieldValue, List.of(object))));

        return result;
    }


    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return List.of(refValue.getRefClass());
    }
}
