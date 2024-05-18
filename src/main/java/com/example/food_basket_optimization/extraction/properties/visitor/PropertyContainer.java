package com.example.food_basket_optimization.extraction.properties.visitor;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.ReferenceObject;

import java.util.List;

public interface PropertyContainer extends ReferenceObject {
    List<List<Property<?>>> getResolvedProperties(List<ExtractedEntity> refEntities);
}
