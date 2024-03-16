package com.example.food_basket_optimization.extraction.properties.root;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.SourceHttp;
import com.example.food_basket_optimization.extraction.properties.sourceresolver.SourceHttpResolver;
import com.example.food_basket_optimization.extraction.properties.mapping.htmlmap.HtmlMapper;
import com.example.food_basket_optimization.extraction.properties.mapping.Mapper;

import java.util.List;

/**
 * Represent object to get controller parsing over HTTP, information will be received as Html
 */
public class HttpHtmlProperties implements ExtractionProperties<ResolvableSource<SourceHttp>> {
    private final SourceHttpResolver sourceResolver;
    private final HtmlMapper mapper;
    private final ResolvableSource<SourceHttp> resolvableSource;
    private final Class<? extends ExtractedEntity> classToParse;

    public HttpHtmlProperties(SourceHttpResolver sourceResolver,
                              HtmlMapper mapper,
                              ResolvableSource<SourceHttp> resolvableSource,
                              Class<? extends ExtractedEntity> classToParse) {
        this.sourceResolver = sourceResolver;
        this.mapper = mapper;
        this.resolvableSource = resolvableSource;
        this.classToParse = classToParse;
    }


    @Override
    public ResolvableSource<SourceHttp> getParsedSource() {
        return resolvableSource;
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
    public SourceHttpResolver getSourceResolver() {
        return sourceResolver;
    }



    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
       return resolvableSource.getRefClasses();
    }
}
