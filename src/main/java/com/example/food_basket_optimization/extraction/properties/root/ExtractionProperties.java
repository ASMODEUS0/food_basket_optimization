package com.example.food_basket_optimization.extraction.properties.root;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extraction.properties.source.ParsedSourceContract;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;
import com.example.food_basket_optimization.extraction.properties.sourceresolver.SourceResolverContract;
import com.example.food_basket_optimization.extraction.properties.mapping.Mapper;

/**
 * Describes an object that displays properties that can be used to take control
 * over the parsing of an entity. Parsing occurs entities of the same type
 */
public interface ExtractionProperties<T extends ResolvableSource<? extends ParsedSourceContract>> extends ReferencedExtraction {
    Class<? extends ExtractedEntity> getClassToExtract();
    Mapper getMapper();
    T getParsedSource();
    SourceResolverContract<T> getSourceResolver();

}
