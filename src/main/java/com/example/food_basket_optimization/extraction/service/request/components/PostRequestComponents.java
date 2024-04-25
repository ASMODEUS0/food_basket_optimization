package com.example.food_basket_optimization.extraction.service.request.components;

import org.apache.hc.core5.http.ContentType;

import java.io.InputStream;

public interface PostRequestComponents extends RequestComponents{

    InputStream getBodyStream();
    ContentType getContentType();
}
