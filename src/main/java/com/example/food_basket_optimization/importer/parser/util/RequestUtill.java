package com.example.food_basket_optimization.importer.parser.util;

import com.example.food_basket_optimization.importer.parser.parsedproperties.SourceHttp;
import com.example.food_basket_optimization.importer.parser.parsedproperties.url.KeyValueUrlBasic;
import com.example.food_basket_optimization.importer.parser.parsedproperties.url.KeyValueUrlProperty;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.*;

public class RequestUtill {

    public static void main(String[] args) {
        String[] arr = new String[1];
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://dixy.ru/catalog/"))
                .GET()
                .headers(arr)
                .build();
    }

    public static URI addQuerryParams(URI uri, List<? extends KeyValueUrlProperty> params) {
        if (params.isEmpty()) {
            return uri;
        }
        String path = uri.toString();
        for (KeyValueUrlProperty param : params) {
            if (path.contains("?")) {
                path += "&" + param.getKey() + "=" + param.getValue();
            }
            path += "?" + param.getKey() + "=" + param.getValue();
        }
        return URI.create(path);
    }

    public static HttpRequest convertFrom(SourceHttp source) {

        List<? extends KeyValueUrlProperty> params = source.getParams();
        URI uri = source.getUri();
        URI uriParameterized = addQuerryParams(uri, params);

        List<KeyValueUrlProperty> headers = source.getHeaders();
        ArrayList<String> headersKeyValue = new ArrayList<>();

        headers.forEach(header -> {
            headersKeyValue.add(header.getKey());
            headersKeyValue.add(header.getValue());
        });


//        headers.stream().map().collect(Coll)

//        String[] array = headersKeyValue.toArray(String[]::new).length == 0 ? ;

        HttpRequest.Builder builder = null;
        switch (source.getMethod()) {
            case POST -> {
                builder =  HttpRequest.newBuilder(uriParameterized)
                        .POST(HttpRequest.BodyPublishers.ofString(source.getBody()));
            }
            default -> {
                builder = HttpRequest.newBuilder(uriParameterized)
                        .GET();
            }
        }

        return buildHttpRequestWithHeaders(headersKeyValue, builder);

    }

    private static HttpRequest buildHttpRequestWithHeaders(List<String> headers, HttpRequest.Builder buider) {
        Objects.requireNonNull(headers);
        if (!headers.isEmpty()) {
            buider.headers(headers.toArray(String[]::new));
        }
        return buider.build();

    }
}
