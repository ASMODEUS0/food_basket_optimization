package com.example.food_basket_optimization.extraction.properties.base.multi;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extraction.properties.base.simple.SimpleString;
import com.example.food_basket_optimization.extraction.properties.base.simple.StringProperty;
import com.example.food_basket_optimization.extraction.properties.util.ExtractUtil;
import com.example.food_basket_optimization.extraction.properties.util.RefValue;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
@RequiredArgsConstructor
public class ContextualStringProperty implements MultiString, ReferencedExtraction {


    private final java.lang.String element;
    private final ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext;
    private final RefValue refValue;


    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return List.of(refValue.getRefClass());
    }


    @Override
    public List<SimpleString> multiply() {
        List<SimpleString> result = new ArrayList<>();
        Map<? extends ExtractedEntity, Object> objectFieldValueMap = ExtractUtil.getValueFromField(refValue.getRefClass(), refValue.getFieldName(), extractContext);
        objectFieldValueMap.forEach((object, fieldValue) -> result.add(new StringProperty(element + fieldValue, List.of(object))));
        return result;
    }

}
