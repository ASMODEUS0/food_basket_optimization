package com.example.food_basket_optimization.importer.parser.parsedobject;

import com.example.food_basket_optimization.importer.parser.parsedobject.url.Param;
import com.example.food_basket_optimization.importer.parser.parsedobject.url.Header;
import com.example.food_basket_optimization.importer.parser.parsedobject.url.HttpMethod;
import com.example.food_basket_optimization.importer.parser.service.RequestService;
import lombok.Getter;

import java.net.URI;
import java.util.List;

@Getter
public class HTTPJsonParsedObject extends HttpObject implements JsonParsedObject {

    private String className;
    private boolean isList;

    public HTTPJsonParsedObject(List<Header> headers, URI uri, HttpMethod httpMethod, String body, String className, boolean isList, List<Param> params) {
        super(headers, uri, httpMethod, body, params);
        this.className = className;
        this.isList = isList;

    }

    @Override
    public Class<?> getClassToParse() {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getData() {
        return RequestService.doRequest(this);
    }

    @Override
    public boolean isList() {
        return isList;
    }

//    @Override
//    public <T extends HttpObject> T getCopy(HttpObject object) {
//        return null;
//    }
}
