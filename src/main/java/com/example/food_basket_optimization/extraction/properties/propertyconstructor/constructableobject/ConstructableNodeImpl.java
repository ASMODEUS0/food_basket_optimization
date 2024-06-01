package com.example.food_basket_optimization.extraction.properties.propertyconstructor.constructableobject;

import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.PropertySource;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.propertyconstructor.DefaultPropertyConstructor;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.propertyconstructor.PropertyConstructor;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.stream.Collectors;

public class ConstructableNodeImpl<T extends SimpleProperty<?>> extends ConstructableNodeAbs<T> implements ConstructableNode<T>  {

    public ConstructableNodeImpl(Constructor<T> constructor, List<Property> params) {
        super(constructor, params);
    }

    @Override
    public List<PropertyConstructor<T>> constructObject() {
        List<PropertySource> sources = getSource();
        return sources.stream().map(source -> new DefaultPropertyConstructor<>(source, constructor)).collect(Collectors.toList());
    }

}
