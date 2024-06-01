package com.example.food_basket_optimization.extraction.properties.base.simple;

import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.SimplePropertyAbs;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.SourceHttp;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValue;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueProperty;
import com.example.food_basket_optimization.extraction.service.request.util.URIConstructor;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.List;


public class SourceHttpProperty extends SimplePropertyAbs<SourceHttp> {


    public static Constructor<SourceHttpProperty> getConstructor() {
        try {
            return SourceHttpProperty.class.getConstructor(SimpleProperty.class, HttpMethod.class, SimpleProperty.class, ListedKeyValueProperty.class, ListedKeyValueProperty.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }


    private final SimpleProperty<URL> url;
    private final HttpMethod method;
    private final SimpleProperty<String> body;
    private final ListedKeyValueProperty params;
    private final ListedKeyValueProperty headers;

    public SourceHttpProperty(SimpleProperty<URL> url,
                              HttpMethod method,
                              SimpleProperty<String> body,
                              ListedKeyValueProperty params,
                              ListedKeyValueProperty headers) {
        this.url = url;
        this.method = method;
        this.body = body;
        this.params = params;
        this.headers = headers;


        addRefEntities(url.getReferenceEntities());
        addRefEntities(method.getReferenceEntities());
        addRefEntities(body.getReferenceEntities());
        addRefEntities(params.getReferenceEntities());
        addRefEntities(headers.getReferenceEntities());
    }

    @Override
    public SourceHttp property() {
        List<KeyValue> params = this.params.property().stream().map(KeyValueProperty::property).toList();
        List<KeyValue> headers = this.headers.property().stream().map(KeyValueProperty::property).toList();
        return new SourceHttp(URIConstructor.convertKeyValue(url.property(), params),
                headers,
                method,
                body.property());
    }
}
