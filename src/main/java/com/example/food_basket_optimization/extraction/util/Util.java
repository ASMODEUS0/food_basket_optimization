package com.example.food_basket_optimization.extraction.util;

import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.base.JsonProperty;
import com.example.food_basket_optimization.extraction.properties.base.keyvalue.basic.KeyValueBasicProperty;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Util {


    public static void main(String[] args) {

        ArrayList<KeyValueBasicProperty> properties = new ArrayList<>();
        properties.add(new KeyValueBasicProperty("", ""));
        properties.add(new KeyValueBasicProperty("", ""));
        properties.add(new KeyValueBasicProperty("", ""));


        Property<?> objectFromMultipliedParams = createObjectFromMultipliedParams(JsonProperty.getConstructor(), properties);
    }

    public static <T> T createObjectFromMultipliedParams(Constructor<T> objectConstructor, List<?> multipliedParams) {


        try {
            Object[] array = multipliedParams.toArray();
            return objectConstructor.newInstance((Object) array);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public static <T, S> T createObjectFromMultipliedParams2(Constructor<T> objectConstructor, List<S> multipliedParams) {


        try {

            KeyValueBasicProperty[] array = multipliedParams.toArray(new KeyValueBasicProperty[0]);
            return objectConstructor.newInstance((Object) array);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
