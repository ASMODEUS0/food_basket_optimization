package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url;

import com.example.food_basket_optimization.extraction.ExtractedEntity;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UrlBasicProperty extends UrlPropertiesAbs implements UrlProperty {

    private final List<ExtractedEntity> referenceEntities;

    public UrlBasicProperty(String protocol, String path, String host) {
        super(protocol, path, host);
        this.referenceEntities = new ArrayList<>();
    }

    public UrlBasicProperty(URL url){
        super(url.getProtocol(), url.getPath(), url.getHost());
        this.referenceEntities = new ArrayList<>();
    }

    public UrlBasicProperty(String protocol, String path, String host, List< ExtractedEntity> referenceEntities) {
        super(protocol, path, host);
        this.referenceEntities = referenceEntities;
    }

    public UrlBasicProperty(URL url, List<ExtractedEntity> referenceEntities){
        super(url.getProtocol(), url.getPath(), url.getHost());
        this.referenceEntities = referenceEntities;
    }


    @Override
    public List<ExtractedEntity> getReferenceEntities() {
        return referenceEntities;
    }

    @Override
    public URL property() {
        return this.getUrl();
    }
}
