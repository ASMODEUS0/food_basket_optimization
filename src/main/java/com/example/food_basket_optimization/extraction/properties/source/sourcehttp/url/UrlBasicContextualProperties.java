package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.util.ReferencedProperty;

import java.util.List;

public class UrlBasicContextualProperties extends UrlBasicProperties implements ReferencedProperty {
    private final List<? extends ExtractedEntity> refEntities;

    protected UrlBasicContextualProperties(String protocol, String path, String host, List<? extends ExtractedEntity> refEntities) {
        super(protocol, path, host);
        this.refEntities = refEntities;
    }


    @Override
    public List<? extends ExtractedEntity> getRefProperties() {
        return refEntities;
    }
}
