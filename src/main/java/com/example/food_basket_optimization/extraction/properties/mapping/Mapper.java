package com.example.food_basket_optimization.extraction.properties.mapping;

import com.example.food_basket_optimization.extraction.ExtractedEntity;

import java.util.List;


/**
 * Represent base class for  mapper, witch extracting Object from properties
 */
public interface Mapper {
   List<ExtractedEntity> map(List<? extends MapProperty> properties);
}
