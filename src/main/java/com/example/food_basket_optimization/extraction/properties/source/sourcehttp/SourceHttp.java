package com.example.food_basket_optimization.extraction.properties.source.sourcehttp;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.base.simple.SimpleString;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.request.RequestProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.HttpMethod;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueUrlBasic;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlProperties;

import java.util.List;


public class SourceHttp implements SourceHttpContract {

    private final UrlProperties url;
    private final HttpMethod method;
    private final SimpleString body;
    private final List<KeyValueUrlBasic> params;
    private final List<KeyValueUrlBasic> headers;
    private final List<ExtractedEntity> referenceEntities;

    public SourceHttp(UrlProperties url,
                      HttpMethod method,
                      SimpleString body,
                      List<KeyValueUrlBasic> params,
                      List<KeyValueUrlBasic> headers,
                      List<ExtractedEntity> referenceEntities) {

        this.url = url;
        this.method = method;
        this.body = body;
        this.params = params;
        this.headers = headers;
        this.referenceEntities = referenceEntities;
    }


    @Override
    public List<KeyValueUrlBasic> getParams() {
        return params;
    }

    @Override
    public List<KeyValueUrlBasic> getHeaders() {
        return headers;
    }

    @Override
    public UrlProperties getUrl() {
        return this.url;
    }

    @Override
    public HttpMethod getMethod() {
        return method;
    }

    @Override
    public SimpleString getBody() {
        return body;
    }


    @Override
    public List<ExtractedEntity> getReferenceEntities() {
        return referenceEntities;
    }
}
