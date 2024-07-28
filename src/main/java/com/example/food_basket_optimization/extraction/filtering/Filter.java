package com.example.food_basket_optimization.extraction.filtering;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Filter {

    public <T> void filter(List<T> objects, List<FilterRule> rules) {
        rules.forEach(rule -> filter(objects, rule));
    }

    public <T> void filter(List<T> objects, FilterRule rule) {
        if (Objects.requireNonNull(rule.getFilterType()) == FilterType.UNIQUE) {
            filterUnique(objects, rule.getField());
        }
    }

    private <T> void filterUnique(List<T> objects, Field field) {
        ArrayList<T> objectsCopy = new ArrayList<>(objects);
        field.setAccessible(true);
        HashSet<Object> fieldValues = new HashSet<>();

        objectsCopy.forEach(object -> {
            try {
                Object fieldValue = ReflectionUtils.getField(field, object);
                if (fieldValues.contains(fieldValue)) {
                    objects.remove(object);
                } else {
                    fieldValues.add(fieldValue);
                }
            } catch (Exception ignored) {
            }

        });
    }

}
