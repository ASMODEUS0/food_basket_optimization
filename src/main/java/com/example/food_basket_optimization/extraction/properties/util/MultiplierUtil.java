package com.example.food_basket_optimization.extraction.properties.util;

import com.example.food_basket_optimization.extraction.util.Util;
import org.apache.commons.lang.IncompleteArgumentException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

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
                    })).collect(Collectors.toList());
        }

        return result;
    }

    public static <T> List<List<T>> equalizationOfParams(List<List< T>> params, List<T> newParam) {




        return null;
    }


    public static <T> T createPropertyFromMultipliedPropertiesParams(List<?> params, Class<T> propertyClass){

        List<? extends Class<?>> fieldTypes = Arrays.stream(propertyClass.getDeclaredFields()).map(Field::getType).toList();

        List<Object> paramsInTheRightOrder = placeParamsInTheRightOrder(params, fieldTypes);

        try {
            Constructor<T> constructor = propertyClass.getConstructor(fieldTypes.toArray(new Class[]{}));
            return Util.createObjectFromMultipliedParams(constructor, paramsInTheRightOrder);
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
