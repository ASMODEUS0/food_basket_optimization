package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlBasicProperties extends UrlPropertiesAbs implements UrlProperties{
    public UrlBasicProperties(String protocol, String path, String host) {
        super(protocol, path, host);
    }

    public UrlBasicProperties(URL url){
        super(url.getProtocol(), url.getPath(), url.getHost());
    }


}
