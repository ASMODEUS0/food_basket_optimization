package com.example.food_basket_optimization.extractpojo.extractedentity.lenta;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtractedEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LentaCategorySize implements ExtractedEntity, ReferencedExtractedEntity {
    public LentaCategoryExt category;
    @JsonProperty("total")
    public int size;

    @Override
    public boolean referencesIsEqual(List<ExtractedEntity> references) {
        return references.stream().anyMatch(ref -> ref == category);
    }
}
