package com.example.food_basket_optimization.extraction.properties.source.sourcehttp;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extraction.properties.multi.PropertyBuilder;
import com.example.food_basket_optimization.extraction.properties.source.HttpExtractionSource;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;
import com.example.food_basket_optimization.extraction.properties.util.MultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.util.PostMultiplyingProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The object is responsible for propagating the properties of the http request,
 * based on the specified parameters. These can be contextual matches or parameters
 * based on numerical patterns.
 */
public class ResolvableSourceHttpProperties implements ResolvableSource<HttpExtractionSource> {
    private final List<MultiplyingProperty<?>> multiParams;
    private final List<PostMultiplyingProperty<?>> postMultiParams;

    public ResolvableSourceHttpProperties(List<MultiplyingProperty<?>> multiParams,
                                          List<PostMultiplyingProperty<?>> postMultiParams) {
        this.multiParams = multiParams;
        this.postMultiParams = postMultiParams;


    }


    @Override
    public List<HttpExtractionSource> resolve() {
        PropertyBuilder<SourceHttpProperty> sourceHttpPropertyBuilder = new PropertyBuilder<>(multiParams, postMultiParams, SourceHttpProperty.class);
        List<SourceHttpProperty> sources = sourceHttpPropertyBuilder.build();
        return sources.stream().map(source -> new HttpExtractionSource(source.property(), source.getReferenceEntities())).toList();

    }

    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        ArrayList<Class<? extends ExtractedEntity>> result = new ArrayList<>(multiParams.stream().map(ReferencedExtraction::getRefClasses).flatMap(Collection::stream).toList());
        result.addAll(postMultiParams.stream().map(ReferencedExtraction::getRefClasses).flatMap(Collection::stream).toList());
        return result;

    }
}
