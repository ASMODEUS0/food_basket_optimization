package com.example.food_basket_optimization.extraction.properties.sourceresolver;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.MapProperty;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;

import java.util.List;

public interface SourceResolverContract<T extends ResolvableSource<?>> {
    List<MapProperty> getData(T resolvableSource, Class<? extends ExtractedEntity> classToParse);
}
