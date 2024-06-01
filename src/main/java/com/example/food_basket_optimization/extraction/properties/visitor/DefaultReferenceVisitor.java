package com.example.food_basket_optimization.extraction.properties.visitor;

import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.constructableobject.ConstructableRootObject;

public class DefaultReferenceVisitor implements ReferenceVisitor{

    private final ConstructableRootObject<?> rootObject;

    public DefaultReferenceVisitor(ConstructableRootObject<?> rootObject) {
        this.rootObject = rootObject;
    }

    @Override
    public void visitReferenced(ReferencedExtraction referencedProperty) {
        rootObject.addRefEntitiesTypes(referencedProperty.getRefClasses());
    }

    @Override
    public void visit(Property property) {

    }
}
