package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url;

import java.net.URL;

/**
 * Represents properties for getting a java.net.URL object
 */
public interface UrlProperties {
    String getProtocol();
    String getHost();
    String getPath();
    URL getUrl();
}
