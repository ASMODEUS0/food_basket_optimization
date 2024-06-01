package com.example.food_basket_optimization.extraction.properties.propertyconstructor.constructableobject;

import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.propertyconstructor.PropertyConstructor;

import java.util.List;


public interface ConstructableNode<T extends SimpleProperty<?>> extends ConstrucatbleObject , Property {
     List<PropertyConstructor<T>> constructObject();
}
