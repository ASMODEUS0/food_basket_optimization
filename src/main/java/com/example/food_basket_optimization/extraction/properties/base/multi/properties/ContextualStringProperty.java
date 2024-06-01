package com.example.food_basket_optimization.extraction.properties.base.multi.properties;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.base.multi.MultiString;
import com.example.food_basket_optimization.extraction.properties.base.simple.StringProperty;
import com.example.food_basket_optimization.extraction.properties.util.ExtractUtil;
import com.example.food_basket_optimization.extraction.properties.util.RefValue;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiVisitor;
import com.example.food_basket_optimization.extraction.properties.visitor.ReferenceVisitor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
@RequiredArgsConstructor
public class ContextualStringProperty implements MultiString, ReferencedExtraction {


    private final String element;
    private final ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext;
    private final RefValue refValue;


    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return List.of(refValue.getRefClass());
    }


    @Override
    public List<SimpleProperty<String>> multiply() {
        List<SimpleProperty<String>> result = new ArrayList<>();
        Map<? extends ExtractedEntity, Object> objectFieldValueMap = ExtractUtil.getFieldValueFromContext(refValue.getRefClass(), refValue.getFieldName(), extractContext);
        objectFieldValueMap.forEach((object, fieldValue) -> result.add(new StringProperty(element + fieldValue, List.of(object))));
        return result;
    }

    @Override
    public void visit(MultiVisitor visitor) {
        visitor.multi(this);
    }

    @Override
    public void visit(ReferenceVisitor visitor) {
        visitor.visitReferenced(this);
    }
}
