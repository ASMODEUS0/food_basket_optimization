package com.example.food_basket_optimization.extraction.properties.propertyconstructor.constructableobject;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.PropertySource;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.propertyconstructor.DefaultPropertyConstructor;
import com.example.food_basket_optimization.extraction.properties.visitor.DefaultReferenceVisitor;
import com.example.food_basket_optimization.extraction.properties.visitor.PropertyVisitor;

import java.lang.reflect.Constructor;
import java.util.*;

public class ConstructableRootObjectImpl<T extends SimpleProperty<?>> implements ConstructableRootObject<T> {


    private final Constructor<T> constructor;
    private final List<Property> params;
    private final HashSet<Class<? extends ExtractedEntity>> refClasses = new HashSet<>();


    public ConstructableRootObjectImpl(Constructor<T> constructor
            , List<Property> params) {

        DefaultReferenceVisitor visitor = new DefaultReferenceVisitor(this);
        params.forEach(param -> param.visit(visitor));

        this.params = params;
        this.constructor = constructor;

    }

    @Override
    public List<T> construct() {
        PropertyVisitor propertyVisitor = new PropertyVisitor();

        params.forEach(param -> param.visit(propertyVisitor));

        List<PropertySource> sources = propertyVisitor.getPropertyContainer().getSources();

        List<DefaultPropertyConstructor<T>> propertyConstructor = sources.stream()
                .map(source -> new DefaultPropertyConstructor<>(source, constructor))
                .toList();

        return propertyConstructor.stream().map(constructedObject -> constructedObject.postConstruct(List.of())).flatMap(Collection::stream).toList();
    }


    @Override
    public void addRefEntitiesTypes(List<Class<? extends ExtractedEntity>> refEntitiesTypes) {
        this.refClasses.addAll(refEntitiesTypes);
    }

    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return refClasses.stream().toList();
    }
}
