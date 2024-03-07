package com.example.food_basket_optimization.extraction;

import java.util.List;

public interface ReferencedExtraction {
    List<Class<? extends ExtractedEntity>> getRefClasses();
}
