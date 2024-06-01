package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;


import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.List;

public class UrlBasicProperty extends UrlPropertiesAbs {


    public static Constructor<UrlBasicProperty> getConstructor() {
        try {
            return UrlBasicProperty.class.getConstructor(SimpleProperty.class, SimpleProperty.class, SimpleProperty.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

    public UrlBasicProperty(String protocol, String path, String host) {
        super(protocol, path, host);

    }

    public UrlBasicProperty(URL url) {
        super(url.getProtocol(), url.getPath(), url.getHost());
    }

    public UrlBasicProperty(String protocol, String path, String host, List<ExtractedEntity> referenceEntities) {
        super(protocol, host, path);
        addRefEntities(referenceEntities);
    }

    public UrlBasicProperty(SimpleProperty<String> protocol, SimpleProperty<String>  host, SimpleProperty<String>  path) {
        super(protocol.property(), host.property(), path.property());
        addRefEntities(protocol.getReferenceEntities());
        addRefEntities(path.getReferenceEntities());
        addRefEntities(host.getReferenceEntities());

    }

    public UrlBasicProperty(URL url, List<ExtractedEntity> referenceEntities) {
        super(url.getProtocol(), url.getHost(), url.getPath());
        addRefEntities(referenceEntities);
    }



    @Override
    public URL property() {
        return this.getUrl();
    }


}
