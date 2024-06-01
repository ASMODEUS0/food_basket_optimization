package com.example.food_basket_optimization.extraction.properties.propertyconstructor.constructableobject;

import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.PropertySource;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.propertyconstructor.ListedPropertyConstructor;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.propertyconstructor.PropertyConstructor;

import java.lang.reflect.Constructor;
import java.util.List;

public class ListedConstructableNode<T extends SimpleProperty<?>> extends ConstructableNodeAbs<T> {


    public ListedConstructableNode(Constructor<T> constructor, List<Property> params) {
        super(constructor, params);

    }

    @Override
    public List<PropertyConstructor<T>> constructObject() {
        List<PropertySource> sources = getSource();
        return sources.stream().map(source -> (PropertyConstructor<T>) new ListedPropertyConstructor<>(source, constructor)).toList();
    }

}
