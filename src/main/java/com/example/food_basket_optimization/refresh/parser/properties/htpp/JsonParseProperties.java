package com.example.food_basket_optimization.refresh.parser.properties.htpp;

import com.example.food_basket_optimization.refresh.deserialization.DeserializationObject;
import lombok.Getter;

import java.net.URI;
import java.util.List;

@Getter
public class JsonParseProperties extends HTTPParseProperties {

    private final String className;
    private final String deserializationClassName;

    public JsonParseProperties(List<Header> headers, URI uri, HttpMethod httpMethod, String body, String className, String deserializationClassName) {
        super(headers, uri, httpMethod, body);
        this.className = className;
        this.deserializationClassName = deserializationClassName;

    }
}
