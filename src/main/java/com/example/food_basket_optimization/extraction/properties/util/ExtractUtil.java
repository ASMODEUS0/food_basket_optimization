package com.example.food_basket_optimization.extraction.properties.util;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extractpojo.util.FailedMapping;
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

    public static Map<? extends ExtractedEntity, Object> getFieldValueFromContext(Class<?> clazz,
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

    public static Object getFieldValueFromObject( String fieldName, Object object){
        try {
            Field fieldWithValue = object.getClass().getDeclaredField(fieldName);
            fieldWithValue.setAccessible(true);
            Object value = fieldWithValue.get(object);
            return  value;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
