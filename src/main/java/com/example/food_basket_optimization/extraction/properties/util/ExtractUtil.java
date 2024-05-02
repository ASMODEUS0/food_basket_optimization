package com.example.food_basket_optimization.extraction.properties.util;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extractpojo.util.FailedMapping;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 *Util class to interact with context
 *
 */
@Log4j2
public  class ExtractUtil {

    /**
     * map
     * @param value is must be a json value of type com.example.food_basket_optimization.extractor.parser.util.RefValue.
     *
     * @return Optional of null if json isn't match with RefValue object else.
     */
    public static Optional<RefValue> resolveRefValue(String value){
        if(value == null ){
            return Optional.empty();
        }
        ObjectMapper om = new ObjectMapper();
        try {
            RefValue refValue = om.readValue(value, RefValue.class);
            return Optional.ofNullable(refValue);
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }



    public static Map<? extends ExtractedEntity, Object> getValueFromField(Class<?> clazz,
                                                                 String fieldName,
                                                                 ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext) {
        List<? extends ExtractedEntity> extractedObjects = extractContext.get(clazz);
        if (extractedObjects == null) {
            throw new IllegalStateException("The context does not match the specified properties, class with name: " + clazz.getName() + "missing in context");
        }

        //todo: resolving failed mapping;
        return extractedObjects.stream().map(object -> {
            if(object instanceof FailedMapping){
                return new AbstractMap.SimpleEntry<>(object, "empty");
            }
            try {
                Field fieldWithValue = object.getClass().getDeclaredField(fieldName);
                fieldWithValue.setAccessible(true);
                Object value = fieldWithValue.get(object);
                return new AbstractMap.SimpleEntry<>(object, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey,
                AbstractMap.SimpleEntry::getValue));
    }



    public static List<Class<? extends ExtractedEntity>> detectObjectReferences(Object mayBeReferenced) {
        return mayBeReferenced instanceof ReferencedExtraction ? ((ReferencedExtraction) mayBeReferenced).getRefClasses() : new ArrayList<>();
    }

    public static List<Class<? extends ExtractedEntity>> detectObjectReferences(Collection<?> mayHaveReferences) {
        return mayHaveReferences.stream().flatMap(object -> detectObjectReferences(object).stream()).toList();
    }


    public static List<Class<? extends ExtractedEntity>> detectObjectReferences(Map<?, List<?>> mayHaveReferences) {
        List<Class<? extends ExtractedEntity>> result = new ArrayList<>();
        mayHaveReferences.forEach((key, value)-> result.addAll(detectObjectReferences(value)));
        return result;
    }


    /**
     * Сhecks whether the json string of the object is RefValue, if it is - returns the class to which the object refers
     * @return list of class in witch list of  RefValue refer.
     */
    public static List<? extends Class<? extends ExtractedEntity>> detectStringReference(List<String> mayBeRefValues){
       return mayBeRefValues.stream().flatMap(value -> resolveRefValue(value).stream()).map(RefValue::getRefClass).toList();
    }



}
