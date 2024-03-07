package com.example.food_basket_optimization.extraction.properties.source.sourcehttp;

import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.HttpMethod;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueUrlBasicProperty;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class SourceHttpPropertiesAbs {
    public static final String PARAMS_FIELD_NAME = "paramProperties";
    public static final String HEADERS_FIELD_NAME = "headerProperties";
    public static final String URL_FIELD_NAME = "urlProperties";

    private final UrlProperties urlProperties;
    private final HttpMethod method;
    private final String body;
    private final List<? extends KeyValueUrlBasicProperty> paramProperties;
    private final List<? extends KeyValueUrlBasicProperty> headerProperties;




    protected SourceHttpPropertiesAbs(UrlProperties urlProperties,
                                      HttpMethod method,
                                      String body,
                                      List<? extends KeyValueUrlBasicProperty> params,
                                      List<? extends KeyValueUrlBasicProperty> headers) {

        this.urlProperties = urlProperties;
        this.method = method;
        this.body = body;
        this.paramProperties = params;
        this.headerProperties = headers;

    }


}
