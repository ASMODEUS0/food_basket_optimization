package com.example.food_basket_optimization.extraction.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Util {

    public static <T> T createObjectFromMultipliedParams(Constructor<T> objectConstructor, List<?> multipliedParams) {
        try {
            Object[] array = multipliedParams.toArray();
            return objectConstructor.newInstance(array);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | IllegalArgumentException e) {
            throw new RuntimeException(e + objectConstructor.toString());
        }
    }
}
