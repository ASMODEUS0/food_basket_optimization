package com.example.food_basket_optimization.extraction.properties.util;
import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.Property;

import java.util.List;

public interface PostMultiplying<T> {
    List<T> multiply(List<? extends ExtractedEntity> relatedParams);


}
