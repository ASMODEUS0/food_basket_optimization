package com.example.food_basket_optimization.extraction.properties.source.sourcehttp;

import com.example.food_basket_optimization.extraction.properties.base.simple.HttpMethod;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValue;
import lombok.Getter;

import java.net.URI;
import java.util.List;

@Getter
public class SourceHttp {
    private final URI uri;
    private final List<KeyValue> headers;
    private final HttpMethod method;
    private final String body;


    public SourceHttp(URI uri, List<KeyValue> headers, HttpMethod method, String body) {
        this.uri = uri;
        this.headers = headers;
        this.method = method;
        this.body = body;
    }

}
