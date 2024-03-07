package com.example.food_basket_optimization.extraction.properties.util;

import com.example.food_basket_optimization.extraction.ExtractedEntity;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

public interface ExtractContextual {
    ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> getExtractContext();

}
