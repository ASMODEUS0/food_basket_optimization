package com.example.food_basket_optimization.extraction;

import java.util.List;

public interface ReferencedExtractedEntity extends ExtractedEntity{
    boolean referencesIsEqual(List<ExtractedEntity> references);
}
