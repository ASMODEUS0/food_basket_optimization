package com.example.food_basket_optimization.extraction.properties.propertyconstructor;

import com.example.food_basket_optimization.extraction.ExtractedEntity;

import java.util.List;

public interface ConstructableRootObject {
    void setRefExtractedEntities(List<ExtractedEntity> refEntities);
    List<ExtractedEntity> getRefEntities();
}
