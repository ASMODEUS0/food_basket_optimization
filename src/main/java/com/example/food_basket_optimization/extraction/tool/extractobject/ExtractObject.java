package com.example.food_basket_optimization.extraction.tool.extractobject;


import com.example.food_basket_optimization.extraction.ExtractRuler;
import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.filtering.Filter;
import com.example.food_basket_optimization.extraction.properties.mapping.MapProperty;
import com.example.food_basket_optimization.extraction.properties.mapping.Mapper;
import com.example.food_basket_optimization.extraction.properties.root.ExtractionProperties;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;
import com.example.food_basket_optimization.extraction.properties.sourceresolver.SourceResolverContract;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.List;
@Slf4j
public class ExtractObject<T extends ResolvableSource<?>> implements ExtractObjectContract {


    private final ExtractRuler extractRuler;
    private final ExtractionProperties<T> properties;
    private final Filter filter;

    public ExtractObject(ExtractRuler extractRuler, ExtractionProperties<T> properties) {
        this.extractRuler = extractRuler;
        this.properties = properties;
        filter = new Filter();
    }


    @Override
    public List<? extends ExtractedEntity> parse() {
        log.info("Start extracting objects of class: " + properties.getExtractionClass());
        T parsedSource = properties.getParsedSource();
        SourceResolverContract<T> sourceResolver = properties.getSourceResolver();
        List<MapProperty> mapProperties = sourceResolver.getData(parsedSource, properties.getExtractionClass());
        Mapper mapper = properties.getMapper();
        List<? extends ExtractedEntity> result = mapper.map(mapProperties);
        filter.filter(result, properties.getFilterRules());
        extractRuler.getExtractContext().put(properties.getExtractionClass(), result);
        extractRuler.getChangeSupport().firePropertyChange(new PropertyChangeEvent(this, null, null, null));
        return result;
    }

    @Override
    public Boolean parseIsPossible() {
        List<Class<? extends ExtractedEntity>> classesContainingInContext = properties.getRefClasses().stream()
                .filter(aClass -> extractRuler.getExtractContext().containsKey(aClass))
                .toList();
        return classesContainingInContext.size() == properties.getRefClasses().size();

    }


}
