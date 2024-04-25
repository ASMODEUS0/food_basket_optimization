package com.example.food_basket_optimization.extraction.service.request.util;

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
}
