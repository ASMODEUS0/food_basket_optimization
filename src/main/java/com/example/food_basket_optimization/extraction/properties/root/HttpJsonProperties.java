package com.example.food_basket_optimization.extraction.properties.root;


import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.SourceHttpContract;
import com.example.food_basket_optimization.extraction.properties.sourceresolver.SourceResolverContract;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.JsonMapper;
import com.example.food_basket_optimization.extraction.properties.mapping.Mapper;

import java.util.List;

public class HttpJsonProperties implements ExtractionProperties<ResolvableSource<SourceHttpContract>> {

    private final ResolvableSource<SourceHttpContract> resolvableSource;
    private final Class<? extends ExtractedEntity> classToParse;
    private final JsonMapper mapper;
    private final SourceResolverContract<ResolvableSource<SourceHttpContract>> sourceResolver;


    public HttpJsonProperties(ResolvableSource<SourceHttpContract> resolvableSource,
                              Class<? extends ExtractedEntity> classToParse,
                              JsonMapper mapper,
                              SourceResolverContract<ResolvableSource<SourceHttpContract>> sourceResolver) {
        this.resolvableSource = resolvableSource;
        this.classToParse = classToParse;
        this.mapper = mapper;
        this.sourceResolver = sourceResolver;
    }


    @Override
    public Class<? extends ExtractedEntity> getClassToExtract() {
        return classToParse;
    }

    @Override
    public Mapper getMapper() {
        return mapper;
    }

    @Override
    public ResolvableSource<SourceHttpContract> getParsedSource() {
        return resolvableSource;
    }

    @Override
    public SourceResolverContract<ResolvableSource<SourceHttpContract>> getSourceResolver() {
        return sourceResolver;
    }


    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return resolvableSource.getRefClasses();
    }
}
