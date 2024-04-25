package com.example.food_basket_optimization.extraction.service.request.task;

import com.example.food_basket_optimization.extraction.service.request.HttpResponse;
import lombok.Getter;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;

@Getter
public record DefaultHttpRequestTaskResult(HttpClient client,
                                           HttpUriRequestBase request,
                                           HttpResponse response) {
}
