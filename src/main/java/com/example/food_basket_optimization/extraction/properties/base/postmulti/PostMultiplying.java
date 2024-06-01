package com.example.food_basket_optimization.extraction.properties.base.postmulti;
import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;

import java.util.List;

public interface PostMultiplying<T> extends Property {
    List<T> multiply(List<? extends ExtractedEntity> relatedParams);
}
