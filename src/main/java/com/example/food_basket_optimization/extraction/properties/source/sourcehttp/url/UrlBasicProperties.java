package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url;

import com.example.food_basket_optimization.extraction.ExtractedEntity;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UrlBasicProperties extends UrlPropertiesAbs implements UrlProperties{

    private final List<ExtractedEntity> referenceEntities;

    public UrlBasicProperties(String protocol, String path, String host) {
        super(protocol, path, host);
        this.referenceEntities = new ArrayList<>();
    }

    public UrlBasicProperties(URL url){
        super(url.getProtocol(), url.getPath(), url.getHost());
        this.referenceEntities = new ArrayList<>();
    }

    public UrlBasicProperties(String protocol, String path, String host, List< ExtractedEntity> referenceEntities) {
        super(protocol, path, host);
        this.referenceEntities = referenceEntities;
    }

    public UrlBasicProperties(URL url, List<ExtractedEntity> referenceEntities){
        super(url.getProtocol(), url.getPath(), url.getHost());
        this.referenceEntities = referenceEntities;
    }


    @Override
    public List<ExtractedEntity> getReferenceEntities() {
        return referenceEntities;
    }

    @Override
    public URL getProperty() {
        return this.getUrl();
    }
}
