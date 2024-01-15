package com.example.food_basket_optimization.refresh.parser.properties.htpp;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

public class HTMLParseProperties extends HTTPParseProperties {


    public HTMLParseProperties(List<Header> headers, URI uri, HttpMethod httpMethod, String body) {
        super(headers, uri, httpMethod, body);
    }
}
