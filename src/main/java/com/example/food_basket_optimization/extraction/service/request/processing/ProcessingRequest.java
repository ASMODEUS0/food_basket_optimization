package com.example.food_basket_optimization.extraction.service.request.processing;

import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;

@FunctionalInterface
public interface ProcessingRequest {
    void processing(CloseableHttpClient client, HttpUriRequestBase request);
}
