package com.example.food_basket_optimization.extraction.properties.base.keyvalue.multi;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.base.keyvalue.basic.KeyValueBasicProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.SimpleString;
import com.example.food_basket_optimization.extraction.properties.base.simple.StringProperty;
import com.example.food_basket_optimization.extraction.properties.util.ExtractUtil;
import com.example.food_basket_optimization.extraction.properties.util.MultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.util.RefValue;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class KeyContextualValue implements MultiplyingProperty<KeyValueBasicProperty> {

    private final String key;
    private final ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext;
    private final RefValue refValue;


    public KeyContextualValue(String key, ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext, RefValue refValue) {
        this.key = key;
        this.extractContext = extractContext;
        this.refValue = refValue;
    }


    @Override
    public List<KeyValueBasicProperty> multiply() {
        List<KeyValueBasicProperty> result = new ArrayList<>();
        Map<? extends ExtractedEntity, Object> objectFieldValueMap = ExtractUtil.getValueFromField(refValue.getRefClass(), refValue.getFieldName(), extractContext);
        objectFieldValueMap.forEach((object, fieldValue) -> {
                    KeyValueBasicProperty property = new KeyValueBasicProperty(key, fieldValue.toString());
                    property.addRefEntity(object);
                    result.add(property);
        }
        );
        return result;
    }


    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return List.of(refValue.getRefClass());
    }


    @Override
    public void visit(MultiVisitor visitor) {
        visitor.multi(this);
    }
}
