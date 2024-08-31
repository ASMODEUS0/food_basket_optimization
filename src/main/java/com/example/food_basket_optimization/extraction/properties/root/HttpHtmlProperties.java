package com.example.food_basket_optimization.extraction.properties.root;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.filtering.FilterRule;
import com.example.food_basket_optimization.extraction.filtering.FilterType;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.HttpExtractionSource;
import com.example.food_basket_optimization.extraction.properties.sourceresolver.SourceHttpResolver;
import com.example.food_basket_optimization.extraction.properties.mapping.htmlmap.HtmlMapper;
import com.example.food_basket_optimization.extraction.properties.mapping.Mapper;
import com.example.food_basket_optimization.extraction.properties.sourceresolver.SourceResolverContract;

import java.util.List;

/**
 * Represent object to get controller parsing over HTTP, information will be received as Html
 */
public class HttpHtmlProperties implements ExtractionProperties<ResolvableSource<HttpExtractionSource> > {
    private final SourceResolverContract<ResolvableSource<HttpExtractionSource> > sourceResolver;
    private final HtmlMapper mapper;
    private final ResolvableSource<HttpExtractionSource>  resolvableSource;
    private final Class<? extends ExtractedEntity> classToParse;

    public HttpHtmlProperties(SourceHttpResolver sourceResolver,
                              HtmlMapper mapper,
                              ResolvableSource<HttpExtractionSource>  resolvableSource,
                              Class<? extends ExtractedEntity> classToParse) {
        this.sourceResolver = sourceResolver;
        this.mapper = mapper;
        this.resolvableSource = resolvableSource;
        this.classToParse = classToParse;
    }


    @Override
    public ResolvableSource<HttpExtractionSource>  getParsedSource() {
        return resolvableSource;
    }

    @Override
    public Class<? extends ExtractedEntity> getExtractionClass() {
        return classToParse;
    }

    @Override
    public Mapper getMapper() {
        return mapper;
    }

    @Override
    public SourceResolverContract<ResolvableSource<HttpExtractionSource> > getSourceResolver() {
        return sourceResolver;
    }
    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
       return resolvableSource.getRefClasses();
    }
    
    @Override
    public List<FilterRule> getFilterRules() {
        return null;
    }

    @Override
    public void addFilterRule(String fieldName, FilterType type, Object... args) {
        //todo: put filtering in abstract class
    }

}
