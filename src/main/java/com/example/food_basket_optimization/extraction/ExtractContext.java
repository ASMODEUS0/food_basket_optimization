package com.example.food_basket_optimization.extraction;


import java.util.List;
import java.util.concurrent.ConcurrentMap;

public interface ExtractContext {
    ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> getExtractContext();
}
