package com.example.food_basket_optimization.extraction.properties.source.sourcehttp;

import com.example.food_basket_optimization.extraction.properties.source.ParsedSourceContract;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.HttpMethod;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueUrlBasic;
import com.example.food_basket_optimization.extraction.properties.util.ReferencedProperty;

import java.net.URL;
import java.util.List;

public interface SourceHttpContract extends ParsedSourceContract, ReferencedProperty {
    List<KeyValueUrlBasic> getParams();
    List<KeyValueUrlBasic> getHeaders();
    URL getUrl();
    HttpMethod getMethod();
    String getBody();
}
