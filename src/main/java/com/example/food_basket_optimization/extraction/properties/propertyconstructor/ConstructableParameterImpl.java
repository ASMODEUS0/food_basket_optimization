package com.example.food_basket_optimization.extraction.properties.propertyconstructor;

import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.ResolvableProperty;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.parameter.ConstructableParameter;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiPropertyContainer;
import com.example.food_basket_optimization.extraction.properties.visitor.PropertyContainer;
import com.example.food_basket_optimization.extraction.properties.visitor.PropertyVisitor;
import com.example.food_basket_optimization.extraction.util.Util;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.stream.Collectors;

public class ConstructableParameterImpl implements ConstructableParameter {

    private final Constructor<? extends Property<?>> constructor;
    private final PropertyContainer propertyContainer;
    private  ConstructableRootObject rootObject;

    public ConstructableParameterImpl(Constructor<? extends Property<?>> constructor,
                                      List<ResolvableProperty> properties) {
        this.constructor = constructor;
        MultiPropertyContainer multiPropertyContainer = new MultiPropertyContainer();
        this.propertyContainer = multiPropertyContainer;

        PropertyVisitor propertyVisitor = new PropertyVisitor(multiPropertyContainer);
        properties.forEach(property->{
            property.visit(propertyVisitor);
        });
    }

    @Override
   public  List<Property<?>> constructParameter() {
        List<List<Property<?>>> listOfParams = propertyContainer.getResolvedProperties(rootObject.getRefEntities());
        return listOfParams.stream().map(params -> Util.createObjectFromMultipliedParams(constructor, params)).collect(Collectors.toList());
    }

    @Override
    public void registerRootObject(ConstructableRootObject rootObject){
        this.rootObject = rootObject;
        flushRefEntitiesToRootObject(rootObject);
    }

    private void flushRefEntitiesToRootObject(ConstructableRootObject rootObject){
        rootObject.setRefExtractedEntities(propertyContainer.getRefEntities());
    }
}
