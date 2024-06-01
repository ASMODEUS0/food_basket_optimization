package com.example.food_basket_optimization.extraction.properties.propertyconstructor.constructableobject;

import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.PropertySource;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiVisitor;
import com.example.food_basket_optimization.extraction.properties.visitor.PropertyContainer;
import com.example.food_basket_optimization.extraction.properties.visitor.PropertyVisitor;
import com.example.food_basket_optimization.extraction.properties.visitor.ReferenceVisitor;

import java.lang.reflect.Constructor;
import java.util.List;

public abstract class ConstructableNodeAbs<T extends SimpleProperty<?>> implements ConstructableNode<T> {

    protected final Constructor<T> constructor;
    private final List<Property> params;

    public ConstructableNodeAbs(Constructor<T> constructor, List<Property> params) {
        this.params = params;
        this.constructor = constructor;
    }

    protected List<PropertySource> getSource() {
        PropertyVisitor visitor = new PropertyVisitor();

        params.forEach(param -> param.visit(visitor));

        PropertyContainer propertyContainer = visitor.getPropertyContainer();

        return propertyContainer.getSources();
    }

    @Override
    public void visit(MultiVisitor visitor) {
        visitor.complex(this);
    }


    @Override
    public void visit(ReferenceVisitor visitor) {
        params.forEach(param -> param.visit(visitor));
    }
}
