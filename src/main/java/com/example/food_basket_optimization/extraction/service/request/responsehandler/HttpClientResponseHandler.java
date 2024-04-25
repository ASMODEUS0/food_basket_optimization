package com.example.food_basket_optimization.extraction.service.request.responsehandler;

import com.example.food_basket_optimization.extraction.service.request.HttpResponse;
import org.apache.hc.core5.http.ClassicHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpClientResponseHandler implements org.apache.hc.core5.http.io.HttpClientResponseHandler<HttpResponse> {
    @Override
    public HttpResponse handleResponse(ClassicHttpResponse response) throws  IOException {
        String contentEncoding = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
        return new HttpResponse(contentEncoding,
                response.getCode());
    }
}
