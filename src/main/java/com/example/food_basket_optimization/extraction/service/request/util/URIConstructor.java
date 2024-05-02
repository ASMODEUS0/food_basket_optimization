package com.example.food_basket_optimization.extraction.service.request.util;

import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValue;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class URIConstructor {
    public static URI convert(URL url, List<String> params) {
        if (!params.isEmpty()){
            throw new IllegalArgumentException();
        }
        try {
            String string = url.toString();
            return new URI(string);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    public static URI convertKeyValue(URL url, List<KeyValue> keyValueParams) {
        List<String> paramList = keyValueParams.stream().map(param -> param.key() + "=" + param.value()).toList();
        return convert(url, paramList);
    }
}
