package com.example.food_basket_optimization.extraction.properties.root;


import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.HttpExtractionSource;
import com.example.food_basket_optimization.extraction.properties.sourceresolver.SourceResolverContract;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.JsonMapper;
import com.example.food_basket_optimization.extraction.properties.mapping.Mapper;

import java.util.List;

public class HttpJsonProperties implements ExtractionProperties<ResolvableSource<HttpExtractionSource>> {

    private final ResolvableSource<HttpExtractionSource> resolvableSource;
    private final SourceResolverContract<ResolvableSource<HttpExtractionSource>> sourceResolver;
    private final JsonMapper mapper;
    private final Class<? extends ExtractedEntity> extractionClass;

    public HttpJsonProperties(ResolvableSource<HttpExtractionSource> resolvableSource,
                              Class<? extends ExtractedEntity> extractionClass,
                              JsonMapper mapper,
                              SourceResolverContract<ResolvableSource<HttpExtractionSource>> sourceResolver) {
        this.resolvableSource = resolvableSource;
        this.extractionClass = extractionClass;
        this.mapper = mapper;
        this.sourceResolver = sourceResolver;
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
    public List<Class< ? extends ExtractedEntity>> getRefClasses() {
        return resolvableSource.getRefClasses();
    }
}
