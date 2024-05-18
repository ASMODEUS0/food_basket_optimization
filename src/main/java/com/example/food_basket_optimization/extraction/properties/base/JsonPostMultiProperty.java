package com.example.food_basket_optimization.extraction.properties.base;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.base.simple.SimpleString;
import com.example.food_basket_optimization.extraction.properties.base.simple.StringProperty;
import com.example.food_basket_optimization.extraction.properties.util.MultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.util.PostMultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiVisitor;


import java.util.*;

 class JsonPostMultiProperty implements PostMultiplyingProperty<SimpleString> {


    private final LinkedHashMap<String, MultiplyingProperty<SimpleString>> elements;

    public JsonPostMultiProperty(LinkedHashMap<String, MultiplyingProperty<SimpleString>> elements) {
        this.elements = elements;
    }

     @Override
     public List<SimpleString> multiply(List<? extends ExtractedEntity> relatedParams) {
         LinkedHashMap<String, List<SimpleString>> multipliedElements = new LinkedHashMap<>();
         elements.forEach((value, object) -> {
             List<SimpleString> multipliedObjects = object.multiply();
             multipliedElements.put(value, multipliedObjects);
         });

         List<LinkedHashMap<String, SimpleString>> multipliedJsons = resolveMulti(multipliedElements);

         return convert(multipliedJsons);
     }




    private List<SimpleString> convert(List<LinkedHashMap<String, SimpleString>> jsons) {
        return jsons.stream().map(this::convert).toList();
    }


    private SimpleString convert(LinkedHashMap<String, SimpleString> json) {
        StringBuilder resultBodyBuilder = new StringBuilder();
        List<ExtractedEntity> referencesEntity = new ArrayList<>();

        resultBodyBuilder.append('{');
        json.forEach((key, object) -> {
            referencesEntity.addAll(object.getReferenceEntities());
            resultBodyBuilder.append('"').append(key).append("\" ").append(':').append('"').append(object.property()).append('"').append(',');
        });
        resultBodyBuilder.deleteCharAt(resultBodyBuilder.lastIndexOf(","));
        resultBodyBuilder.append('}');

        return new StringProperty(resultBodyBuilder.toString(), referencesEntity);
    }


    private List<LinkedHashMap<String, SimpleString>> resolveMulti(LinkedHashMap<String, List<SimpleString>> multipliedJson) {

        List<LinkedHashMap<String, SimpleString>> result = new ArrayList<>(List.of(new LinkedHashMap<>()));

        for (Map.Entry<String, List<SimpleString>> multipliedJsonElement : multipliedJson.entrySet()) {

            List<LinkedHashMap<String, SimpleString>> curResult = new ArrayList<>(result);

            result = curResult.stream()
                    .flatMap(jsonElement -> multipliedJsonElement.getValue().stream().map(multipliedValue -> {
                        LinkedHashMap<String, SimpleString> withMultipliedValue = new LinkedHashMap<>(jsonElement);
                        withMultipliedValue.put(multipliedJsonElement.getKey(), multipliedValue);
                        return withMultipliedValue;
                    }))
                    .toList();
        }
        return result;

    }


    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        List<Class<? extends ExtractedEntity>> result = new ArrayList<>();
        elements.forEach((key, refValue) -> {
            result.addAll(refValue.getRefClasses());
        });
        return result;
    }

    @Override
    public void visit(MultiVisitor visitor) {
        visitor.post(this);
    }


 }
