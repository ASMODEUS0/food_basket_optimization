package com.example.food_basket_optimization.extraction.properties.root;


import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.filtering.FilterRule;
import com.example.food_basket_optimization.extraction.filtering.FilterType;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.HttpExtractionSource;
import com.example.food_basket_optimization.extraction.properties.sourceresolver.SourceResolverContract;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.JsonMapper;
import com.example.food_basket_optimization.extraction.properties.mapping.Mapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class HttpJsonProperties implements ExtractionProperties<ResolvableSource<HttpExtractionSource>> {

    private final ResolvableSource<HttpExtractionSource> resolvableSource;
    private final SourceResolverContract<ResolvableSource<HttpExtractionSource>> sourceResolver;
    private final JsonMapper mapper;
    private final Class<? extends ExtractedEntity> extractionClass;
    private final List<FilterRule> filterRules;


    public HttpJsonProperties(ResolvableSource<HttpExtractionSource> resolvableSource,
                              Class<? extends ExtractedEntity> extractionClass,
                              JsonMapper mapper,
                              SourceResolverContract<ResolvableSource<HttpExtractionSource>> sourceResolver) {
        this.resolvableSource = resolvableSource;
        this.extractionClass = extractionClass;
        this.mapper = mapper;
        this.sourceResolver = sourceResolver;
        filterRules = new ArrayList<>();
    }


    @Override
    public Class<? extends ExtractedEntity> getExtractionClass() {
        return extractionClass;
    }

    @Override
    public Mapper getMapper() {
        return mapper;
    }

    @Override
    public ResolvableSource<HttpExtractionSource> getParsedSource() {
        return resolvableSource;
    }

    @Override
    public SourceResolverContract<ResolvableSource<HttpExtractionSource>> getSourceResolver() {
        return sourceResolver;
    }

    @Override
    public List<FilterRule> getFilterRules() {
        return filterRules;
    }

    @Override
    public void addFilterRule(String fieldName, FilterType type) {
        try {
            Field field = extractionClass.getField(fieldName);
            filterRules.add(new FilterRule(field, type));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Exception: " + e + " Incorrect field name for extraction class");
        }
    }


    @Override
    public List<Class< ? extends ExtractedEntity>> getRefClasses() {
        return resolvableSource.getRefClasses();
    }
}
