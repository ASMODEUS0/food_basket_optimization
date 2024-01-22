package com.example.food_basket_optimization.importer.parser.parsedobject;

import com.example.food_basket_optimization.importer.parser.parsedobject.url.Param;
import com.example.food_basket_optimization.importer.parser.parsedobject.url.Header;
import com.example.food_basket_optimization.importer.parser.parsedobject.url.HttpMethod;
import com.example.food_basket_optimization.importer.parser.service.RequestService;
import lombok.*;

import java.net.URI;
import java.util.List;


@Setter
public class HTTPHtmlParsedObject extends HttpObject implements HtmlParsedObjectContract, Copy<HTTPHtmlParsedObject, HttpObject> {

    public HTTPHtmlParsedObject(List<Header> headers, List<Param> params, URI uri, HttpMethod httpMethod, String body) {
        super(headers, uri, httpMethod, body, params);

    }


//    public HTTPHtmlParsedObject(HttpObject httpObject){
//        super(httpObject.getHeaders(), httpObject.getUri(), httpObject.getHttpMethod(), httpObject.getBody(), httpObject.getParams());
//
//    }

    @Override
    public String htmlText() {
        return RequestService.doRequest(this);
    }



    @Override
    public Class<?> getClassToParse() {
        return null;
    }

    @Override
    public String getData() {
        return null;
    }

    @Override
    public HTTPHtmlParsedObject getCopy(HttpObject object) {
       return new HTTPHtmlParsedObject(object.getHeaders(),
                object.getParams(),
                object.getUri(),
                object.getHttpMethod(),
                object.getBody());
    }
}
