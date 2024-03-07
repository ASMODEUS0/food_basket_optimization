package com.example.food_basket_optimization.extraction.properties.util;

import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.HttpMethod;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueUrlBasic;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.*;

public class RequestUtill {



    public static URI createUriWithQueryParams(URI uri, List<? extends KeyValueUrlBasic> params) {
        if (params.isEmpty()) {
            return uri;
        }
        StringBuilder path = new StringBuilder(uri.toString());
        for (KeyValueUrlBasic param : params) {
            if (path.toString().contains("?")) {
                path.append("&").append(param.key()).append("=").append(param.value());
            }
            path.append("?").append(param.key()).append("=").append(param.value());
        }
        return URI.create(path.toString());
    }



    public static HttpRequest.Builder resolveHttpMethod(HttpRequest.Builder requestBuilder, HttpMethod method, String body) {
        switch (method) {
            case POST -> {
                return requestBuilder.POST(HttpRequest.BodyPublishers.ofString(body));
            }
            default -> {
                return requestBuilder.GET();
            }
        }


    }


    public static HttpRequest.Builder addHeaders(HttpRequest.Builder requestBuilder , List<String> headers){
        if(!headers.isEmpty()){
            requestBuilder.headers(headers.toArray(new String[0]));
        }
        return requestBuilder;
    }
}
