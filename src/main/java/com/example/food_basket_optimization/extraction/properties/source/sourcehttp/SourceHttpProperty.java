package com.example.food_basket_optimization.extraction.properties.source.sourcehttp;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.base.simple.ListedKeyValueProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.SimpleString;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.HttpMethod;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValue;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueUrlProperty;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlProperty;
import com.example.food_basket_optimization.extraction.service.request.util.URIConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;


public class SourceHttpProperty implements Property<SourceHttp> {

    private final UrlProperty url;
    private final HttpMethod method;
    private final SimpleString body;
    private final ListedKeyValueProperty params;
    private final ListedKeyValueProperty headers;
//    private final List<ExtractedEntity> referenceEntities;

    public SourceHttpProperty(UrlProperty url,
                              HttpMethod method,
                              SimpleString body,
                              ListedKeyValueProperty params,
                              ListedKeyValueProperty headers) {
        this.url = url;
        this.method = method;
        this.body = body;
        this.params = params;
        this.headers = headers;
    }

    @Override
    public List<ExtractedEntity> getReferenceEntities() {
        return Stream.of(url.getReferenceEntities(),
                method.getReferenceEntities(),
                body.getReferenceEntities(),
                params.getReferenceEntities(),
                headers.getReferenceEntities()).flatMap(Collection::stream).toList();
    }

    @Override
    public SourceHttp property() {
        List<KeyValue> params = this.params.property().stream().map(KeyValueUrlProperty::property).toList();
        List<KeyValue> headers = this.headers.property().stream().map(KeyValueUrlProperty::property).toList();
        return new SourceHttp(URIConstructor.convertKeyValue(url.property(), params),
                headers,
                method,
                body.property());
    }


}
