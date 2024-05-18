package com.example.food_basket_optimization.extraction.properties.propertyconstructor;

import com.example.food_basket_optimization.extraction.properties.ResolvableProperty;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.parameter.ConstructableParameter;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiPropertyContainer;
import com.example.food_basket_optimization.extraction.properties.visitor.PropertyContainer;
import com.example.food_basket_optimization.extraction.properties.visitor.PropertyVisitor;

import java.util.List;

public abstract class ConstructableParameterAbs implements ConstructableParameter {
    protected final PropertyContainer propertyContainer;
    protected ConstructableRootObject rootObject;


    protected ConstructableParameterAbs(List<ResolvableProperty> properties) {
        MultiPropertyContainer multiPropertyContainer = new MultiPropertyContainer();
        this.propertyContainer = multiPropertyContainer;
        PropertyVisitor propertyVisitor = new PropertyVisitor(multiPropertyContainer);
        properties.forEach(property -> {
            property.visit(propertyVisitor);
        });
    }


    @Override
    public void registerRootObject(ConstructableRootObject rootObject) {
        this.rootObject = rootObject;
        flushRefEntitiesToRootObject(rootObject);
    }

    private void flushRefEntitiesToRootObject(ConstructableRootObject rootObject) {
        rootObject.setRefExtractedEntities(propertyContainer.getRefEntities());
    }
}
