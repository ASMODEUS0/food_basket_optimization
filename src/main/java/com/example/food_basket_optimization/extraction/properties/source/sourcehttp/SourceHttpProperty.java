package com.example.food_basket_optimization.extraction.properties.source.sourcehttp;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.Properties;
import com.example.food_basket_optimization.extraction.properties.base.simple.SimpleString;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.HttpMethod;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValue;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueUrlBasic;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlProperties;
import com.example.food_basket_optimization.extraction.service.request.util.URIConstructor;

import java.util.List;


public class SourceHttpProperty implements Properties<SourceHttp> {

    private final UrlProperties url;
    private final HttpMethod method;
    private final SimpleString body;
    private final List<KeyValueUrlBasic> params;
    private final List<KeyValueUrlBasic> headers;
    private final List<ExtractedEntity> referenceEntities;

    public SourceHttpProperty(UrlProperties url,
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
    public List<ExtractedEntity> getReferenceEntities() {
        return referenceEntities;
    }

    @Override
    public SourceHttp getProperty() {
        List<KeyValue> params = this.params.stream().map(KeyValueUrlBasic::getProperty).toList();
        List<KeyValue> headers = this.headers.stream().map(KeyValueUrlBasic::getProperty).toList();
        return new SourceHttp(URIConstructor.convertKeyValue(url.getProperty(), params),
                headers,
                method,
                body.getProperty());
    }


}
