package com.example.food_basket_optimization.extraction.properties.propertyconstructor.propertyconstructor;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.ReferenceObject;

import java.util.List;

public interface PropertyConstructor<T extends SimpleProperty<?>> extends ReferenceObject {
    List<T> postConstruct(List<ExtractedEntity> refEntities);
}
