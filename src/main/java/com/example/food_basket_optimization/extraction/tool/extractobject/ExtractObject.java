package com.example.food_basket_optimization.extraction.tool.extractobject;


import com.example.food_basket_optimization.extraction.ExtractRuler;
import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.MapProperty;
import com.example.food_basket_optimization.extraction.properties.mapping.Mapper;
import com.example.food_basket_optimization.extraction.properties.root.ExtractionProperties;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;
import com.example.food_basket_optimization.extraction.properties.sourceresolver.SourceResolverContract;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class ExtractObject<T extends ResolvableSource<?>> implements ExtractObjectContract {


    private final ExtractRuler extractRuler;
    private final ExtractionProperties<T> properties;

    public ExtractObject(ExtractRuler extractRuler, ExtractionProperties<T> properties) {
        this.extractRuler = extractRuler;
        this.properties = properties;
    }


    @Override
    public List<? extends ExtractedEntity> parse() {
        T parsedSource = properties.getParsedSource();
        SourceResolverContract<T> sourceResolver = properties.getSourceResolver();
        List<MapProperty> mapProperties = sourceResolver.getData(parsedSource, properties.getClassToExtract());
        Mapper mapper = properties.getMapper();
        List<? extends ExtractedEntity> result = mapper.map(mapProperties);
        extractRuler.getExtractContext().put(properties.getClassToExtract(), result);
        extractRuler.getChangeSupport().firePropertyChange(new PropertyChangeEvent(this, null, null, null));
        return result;
    }

    @Override
    public Boolean parseIsPossible() {
        List<Class<? extends ExtractedEntity>> classesContainingInContext = properties.getRefClasses().stream().filter(aClass -> extractRuler.getExtractContext().containsKey(aClass)).toList();
        return classesContainingInContext.size() == properties.getRefClasses().size();

    }


}
