package com.example.food_basket_optimization.extraction.properties.propertyconstructor.constructableobject;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;

import java.util.List;

public interface ConstructableRootObject<T extends SimpleProperty<?>> extends ConstrucatbleObject, ReferencedExtraction {
    List<T> construct();
    void addRefEntitiesTypes(List<Class<? extends ExtractedEntity>> refEntitiesTypes);

}
