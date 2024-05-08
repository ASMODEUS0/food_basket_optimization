package com.example.food_basket_optimization.extraction.properties.util;

import org.apache.commons.lang.IncompleteArgumentException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MultiplierUtil {

    public static <T> List<List<T>> directProduct(List<List< T>> sets) {

        List<List<T>> result = new ArrayList<>(List.of(new ArrayList<>()));

        for (List<? extends T> set : sets) {
            if (set.isEmpty()) {
                throw new IllegalArgumentException("Multitude can't be empty");
            }

            List<List<T>> curResult = new ArrayList<>(result);

            result = curResult.stream().flatMap(resElements ->
                    set.stream().map(pathElement -> {
                        List<T> stringProperties = new ArrayList<>(resElements);
                        stringProperties.add(pathElement);
                        return stringProperties;
                    })).toList();
        }

        return result;
    }




    public static <T> T createObjectFromMultipliedParams(Constructor<T> objectConstructor, List<Object> multipliedParams) {


            try {
                return objectConstructor.newInstance(multipliedParams.toArray());
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
    }


    public static <T> T createPropertyFromMultipliedPropertiesParams(List<?> params, Class<T> propertyClass){

        List<? extends Class<?>> fieldTypes = Arrays.stream(propertyClass.getDeclaredFields()).map(Field::getType).toList();

        List<Object> paramsInTheRightOrder = placeParamsInTheRightOrder(params, fieldTypes);

        try {
            Constructor<T> constructor = propertyClass.getConstructor(fieldTypes.toArray(new Class[]{}));
            return createObjectFromMultipliedParams(constructor, paramsInTheRightOrder);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<Object> placeParamsInTheRightOrder(List<?> params, List<? extends Class<?>> fieldsOfObject){

        List<Object> result = new ArrayList<>();
        if(params.size() != fieldsOfObject.size()){
            throw new IncompleteArgumentException("The number of parameters does not match the created object");
        }
        fieldsOfObject.forEach(fieldOfObject->{
            Optional<?> first = params.stream().filter(param -> fieldOfObject.isAssignableFrom(param.getClass())).findFirst();
            Object object = first.orElseThrow();
            params.remove(object);
            result.add(object);
        });

        return result;
    }
}
