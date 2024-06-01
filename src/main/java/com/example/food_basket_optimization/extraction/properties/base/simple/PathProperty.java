package com.example.food_basket_optimization.extraction.properties.base.simple;

import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.SimplePropertyAbs;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PathProperty extends SimplePropertyAbs<String> {
    public static Constructor<PathProperty> getConstructor() {
        try {
            return PathProperty.class.getConstructor(List.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }
    private final List<SimpleProperty<String>> elements;

    public PathProperty(List<SimpleProperty<String>> elements) {
        super(elements.stream().map(SimpleProperty::getReferenceEntities).flatMap(Collection::stream).collect(Collectors.toList()));
        this.elements = elements;
    }

    @Override
    public String property() {
        StringBuilder sb = new StringBuilder();
        elements.forEach(element->{
            sb.append("/").append(element.property());
        });
        return sb.toString();
    }


}
