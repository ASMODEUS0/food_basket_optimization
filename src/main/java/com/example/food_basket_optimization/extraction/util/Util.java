package com.example.food_basket_optimization.extraction.util;

import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.base.simple.JsonProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.KeyValueSimpleProperty;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Util {


    public static void main(String[] args) {

        ArrayList<KeyValueSimpleProperty> properties = new ArrayList<>();
        properties.add(new KeyValueSimpleProperty("", ""));
        properties.add(new KeyValueSimpleProperty("", ""));
        properties.add(new KeyValueSimpleProperty("", ""));


        Property objectFromMultipliedParams = createObjectFromMultipliedParams(JsonProperty.getConstructor(), properties);
    }

    public static <T> T createObjectFromMultipliedParams(Constructor<T> objectConstructor, List<?> multipliedParams) {


        try {
            Object[] array = multipliedParams.toArray();
            return objectConstructor.newInstance(array);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | IllegalArgumentException e) {
            throw new RuntimeException(e + objectConstructor.toString());
        }
    }


    public static <T, S> T createObjectFromMultipliedParams2(Constructor<T> objectConstructor, List<S> multipliedParams) {


        try {

            KeyValueSimpleProperty[] array = multipliedParams.toArray(new KeyValueSimpleProperty[0]);
            return objectConstructor.newInstance((Object) array);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
