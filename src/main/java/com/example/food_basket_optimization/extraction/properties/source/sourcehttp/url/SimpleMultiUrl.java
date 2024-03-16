package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url;

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
    public List<UrlProperties> multiply() {
       return List.of(new UrlBasicProperties(protocol, path, host));
    }

}
