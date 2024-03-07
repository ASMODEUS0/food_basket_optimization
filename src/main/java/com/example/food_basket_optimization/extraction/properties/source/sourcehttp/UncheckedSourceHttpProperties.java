package com.example.food_basket_optimization.extraction.properties.source.sourcehttp;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlBasicContextualProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlBasicProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.HttpMethod;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueUrlBasic;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueUrlBasicProperty;
import com.example.food_basket_optimization.extraction.properties.util.ReferencedProperty;
import com.example.food_basket_optimization.extraction.properties.util.ShallowCopy;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class UncheckedSourceHttpProperties extends SourceHttpPropertiesAbs implements ShallowCopy<UncheckedSourceHttpProperties> {


    protected UncheckedSourceHttpProperties(UrlProperties urlProperties, HttpMethod method, String body, List<? extends KeyValueUrlBasicProperty> params, List<? extends KeyValueUrlBasicProperty> headers) {
        super(urlProperties, method, body, params, headers);
    }
    
    protected UncheckedSourceHttpProperties(SourceHttpPropertiesAbs source) {
        super(source.getUrlProperties(),
                source.getMethod(),
                source.getBody(),
                source.getParamProperties(),
                source.getHeaderProperties());
    }


    @Override
    public UncheckedSourceHttpProperties copy() {
        return new UncheckedSourceHttpProperties(getUrlProperties(),
                getMethod(),
                getBody(),
                getParamProperties(),
                getHeaderProperties());
    }


    public SourceHttpContract checkedConvert() {
        List<ExtractedEntity> refEntities = new ArrayList<>();

        List<KeyValueUrlBasic> convertedParams = getParamProperties().stream().map(param -> {
            if (param instanceof KeyValueUrlBasic keyValueUrlBasic) {
                return keyValueUrlBasic;
            } else if (param instanceof ReferencedProperty paramRefProperty) {
                refEntities.addAll(paramRefProperty.getRefProperties());
                return new KeyValueUrlBasic(param.key(), param.value());
            }
            throw new IllegalArgumentException("Failure to check,  " + param.getClass());
        }).toList();


        List<KeyValueUrlBasic> convertedHeaders = getHeaderProperties().stream().map(header -> {
            if (header instanceof KeyValueUrlBasic keyValueUrlBasic) {
                return keyValueUrlBasic;
            }
            if (header instanceof ReferencedProperty headerRefProperty) {
                refEntities.addAll(headerRefProperty.getRefProperties());
                return new KeyValueUrlBasic(header.key(), header.value());
            }
            throw new IllegalArgumentException("Failure to check, " + header.getClass());
        }).toList();

        if(getUrlProperties() instanceof ReferencedProperty refUrlProperty){
            refEntities.addAll(refUrlProperty.getRefProperties());
        }


        return new SourceHttp(getUrlProperties().getUrl(),
                getMethod(),
                getBody(),
                convertedParams,
                convertedHeaders,
                refEntities);
    }




}
