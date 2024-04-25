package com.example.food_basket_optimization.extraction.service.request.task;

import com.example.food_basket_optimization.extraction.service.request.HttpClientContainer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;

@RequiredArgsConstructor
@Getter
public class RequestTask {
    private final HttpClientContainer clientContainer;
    private final HttpUriRequestBase request;
}
