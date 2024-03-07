package com.example.food_basket_optimization.extraction.properties.util;

import java.lang.reflect.Field;
import java.util.*;

public class Multiplicator {


    /**
     *
     * @param objects describe listed argument witch can contain Multiplying values
     * @param field field
     * @param sources
     * @return
     * @param <T>
     * @throws IllegalAccessException
     */
    public static  <T extends ShallowCopy<T>> List<T> resolveListedMultiplication(List<?> objects, Field field, List<T> sources) throws IllegalAccessException {
        List<T> sourcesWithMultipliedObject = new ArrayList<>(sources);
        List<? extends Multiplying<?>> objectsToMultiply = findObjectsToMultiply(objects);
        for (Multiplying<?> objectToMultiply : objectsToMultiply) {
            sourcesWithMultipliedObject = multiplyListedValue(sourcesWithMultipliedObject, field, objectToMultiply);
        }
        return sourcesWithMultipliedObject;
    }

    /**
     *
     * @param object
     * @param field
     * @param sources
     * @return
     * @param <T>
     * @throws IllegalAccessException
     */
    public static <T extends ShallowCopy<T>> List<T> resolveMultiplication(Object object, Field field, List<T> sources) throws IllegalAccessException {
        Optional<Multiplying<?>> mayBeMultiplying = objectNeedToBeMultiplied(object);
        if (mayBeMultiplying.isPresent()) {
            Multiplying<?> multiplyingValue = mayBeMultiplying.get();
            return multiplyValue(sources, field, multiplyingValue);
        }
        return sources;
    }


    /**
     * @param objects         that contain a listed field witch value may need to be multiply
     * @param fieldToMultiply name of listed field with value to multiply
     * @param valueToMultiply point in value that contains in a listed fieldToMultiply that need to be multiplied
     * @param <T>
     * @return return shallow copies of objects witch was multiplied by valueToMultiply
     * @throws IllegalAccessException
     */
    public static  <T extends ShallowCopy<T>> List<T> multiplyListedValue(List<T> objects,
                                                                  Field fieldToMultiply,
                                                                  Multiplying<?> valueToMultiply) throws IllegalAccessException {

        ArrayList<T> multipliedObjects = new ArrayList<>();
        for (T object : objects) {
            fieldToMultiply.setAccessible(true);
            List<?> fieldValues = new ArrayList<>((List<?>) fieldToMultiply.get(object));

            if (!fieldValues.remove(valueToMultiply)) {
                throw new IllegalArgumentException("value to multiply is incorrect");
            }
            List<?> multipliedValues = valueToMultiply.multiply();

            for (Object multipliedValue : multipliedValues) {
                T copy = object.copy();
                List<Object> newFieldValues = new ArrayList<>(fieldValues);
                newFieldValues.add(multipliedValue);
                fieldToMultiply.set(copy, newFieldValues);
                multipliedObjects.add(copy);
            }

        }
        return multipliedObjects;
    }


    public static  <T extends ShallowCopy<T>> List<T> multiplyValue(List<T> objects,
                                                            Field fieldToMultiply,
                                                            Multiplying<?> valueToMultiply) {


        List<?> multipliedValues = valueToMultiply.multiply();
        if (multipliedValues.isEmpty()) {
            return objects;
        }
        fieldToMultiply.setAccessible(true);
       return objects.stream().flatMap(object -> {

            List<T> multipliedObjects = new ArrayList<>();
            multipliedValues.forEach(value -> {
                try {
                    T copy = object.copy();
                    fieldToMultiply.set(copy, value);
                    multipliedObjects.add(copy);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
            return multipliedObjects.stream();
        }).toList();

    }

    public static Optional<Multiplying<?>> objectNeedToBeMultiplied(Object object) {
        if (object instanceof Multiplying<?> multiplyObject) {
            return Optional.of(multiplyObject);
        }
        return Optional.empty();
    }


    public static List<? extends Multiplying<?>> findObjectsToMultiply(List<?> objects) {
        return objects.stream().flatMap(object -> objectNeedToBeMultiplied(object).stream()).toList();
    }


}
