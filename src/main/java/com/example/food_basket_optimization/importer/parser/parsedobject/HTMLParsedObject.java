package com.example.food_basket_optimization.importer.parser.parsedobject;

import com.example.food_basket_optimization.importer.parser.parsedobject.url.Param;
import com.example.food_basket_optimization.importer.parser.parsedobject.url.Header;
import com.example.food_basket_optimization.importer.parser.parsedobject.url.HttpMethod;
import com.example.food_basket_optimization.importer.parser.service.RequestService;

import java.net.URI;
import java.util.List;

public class HTMLParsedObject extends HttpObject implements HtmlParsedObjectContract {

    public HTMLParsedObject(List<Header> headers, List<Param> params, URI uri, HttpMethod httpMethod, String body) {
        super(headers, uri, httpMethod, body, params);

    }

    @Override
    public String htmlText() {
        return RequestService.doRequest(this);
    }
}
