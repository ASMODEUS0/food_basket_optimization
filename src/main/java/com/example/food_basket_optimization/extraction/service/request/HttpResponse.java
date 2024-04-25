package com.example.food_basket_optimization.extraction.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class HttpResponse {
    private final String content;
    private final Integer code;
}
