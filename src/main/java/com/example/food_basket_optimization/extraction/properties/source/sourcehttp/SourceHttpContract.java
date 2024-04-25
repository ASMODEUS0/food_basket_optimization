package com.example.food_basket_optimization.extraction.properties.source.sourcehttp;

import com.example.food_basket_optimization.extraction.properties.base.simple.SimpleString;
import com.example.food_basket_optimization.extraction.properties.source.ParsedSourceContract;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.request.RequestProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.HttpMethod;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueUrlBasic;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlProperties;

import java.util.List;

public interface SourceHttpContract extends ParsedSourceContract {
    List<KeyValueUrlBasic> getParams();
    List<KeyValueUrlBasic> getHeaders();
    UrlProperties getUrl();
    HttpMethod getMethod();
    SimpleString getBody();


}
