package com.example.food_basket_optimization.importer.parser.parsedproperties;

import com.example.food_basket_optimization.importer.parser.parsedproperties.url.HttpMethod;
import lombok.Getter;

import java.net.URI;
@Getter
public abstract class SourceHttpAbs {

    private final URI uri;
    private final HttpMethod method;
    private final   String body;

    protected SourceHttpAbs(URI uri, HttpMethod method, String body) {
        this.uri = uri;
        this.method = method;
        this.body = body;
    }
}
