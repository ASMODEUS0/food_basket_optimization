package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiVisitor;

import java.util.List;

public class SimpleMultiUrl implements UrlMultiProperties {
    private final String protocol;
    private final String host;
    private final String path;

    public SimpleMultiUrl(String protocol, String host, String path) {
        this.protocol = protocol;
        this.host = host;
        this.path = path;
    }

    @Override
    public List<UrlProperty> multiply() {
       return List.of(new UrlBasicProperty(protocol, path, host));
    }

    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return List.of();
    }

    @Override
    public void visit(MultiVisitor visitor) {

    }
}
