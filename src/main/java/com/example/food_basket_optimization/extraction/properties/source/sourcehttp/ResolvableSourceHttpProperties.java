package com.example.food_basket_optimization.extraction.properties.source.sourcehttp;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.base.simple.SourceHttpProperty;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.constructableobject.ConstructableRootObject;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The object is responsible for propagating the properties of the http request,
 * based on the specified parameters. These can be contextual matches or parameters
 * based on numerical patterns.
 */
public class ResolvableSourceHttpProperties implements ResolvableSource<HttpExtractionSource> {

    private final ConstructableRootObject<SourceHttpProperty> constructableSource;

    public ResolvableSourceHttpProperties(ConstructableRootObject<SourceHttpProperty> constructableSource) {

        this.constructableSource = constructableSource;

    }


    @Override
    public List<HttpExtractionSource> resolve() {
        List<SourceHttpProperty> constructedSources = constructableSource.construct();
        return constructedSources.stream()
                .map(source -> new HttpExtractionSource(source.property(), source.getReferenceEntities()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return constructableSource.getRefClasses();

    }
}
