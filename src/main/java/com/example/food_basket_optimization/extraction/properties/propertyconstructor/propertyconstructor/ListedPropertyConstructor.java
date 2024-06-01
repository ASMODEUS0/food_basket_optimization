package com.example.food_basket_optimization.extraction.properties.propertyconstructor.propertyconstructor;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.PropertySource;
import com.example.food_basket_optimization.extraction.util.Util;

import java.lang.reflect.Constructor;
import java.util.*;

public class ListedPropertyConstructor<T extends SimpleProperty<?>> extends PropertyConstructorAbs<T> {


    public ListedPropertyConstructor(PropertySource source, Constructor<T> constructor) {
        super(source, constructor);
    }


    public List<T> postConstruct(List<ExtractedEntity> refEntities){
        List<List<SimpleProperty<?>>> listOfParams = resolvePostConstruct(refEntities);
        return listOfParams.stream().map(params-> Util.createObjectFromMultipliedParams(constructor, List.of(params))).toList();
    }

}
