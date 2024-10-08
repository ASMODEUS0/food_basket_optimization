package com.example.food_basket_optimization.extraction.properties.root;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extraction.filtering.FilterRule;
import com.example.food_basket_optimization.extraction.filtering.FilterType;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;
import com.example.food_basket_optimization.extraction.properties.sourceresolver.SourceResolverContract;
import com.example.food_basket_optimization.extraction.properties.mapping.Mapper;

import java.util.List;

/**
 * Describes an object that displays properties that can be used to take control
 * over the parsing of an entity. Parsing occurs entities of the same type
 */
public interface ExtractionProperties<T extends ResolvableSource<?>> extends ReferencedExtraction {
    Class<? extends ExtractedEntity> getExtractionClass();

    Mapper getMapper();

    T getParsedSource();

    SourceResolverContract<T> getSourceResolver();

    List<FilterRule> getFilterRules();

    void addFilterRule(String fieldName, FilterType type,  Object... args);


}
