package com.example.food_basket_optimization.extraction.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
@RequiredArgsConstructor
@Getter
public class HttpClientContainer {
    private final CloseableHttpClient client;
    private Boolean isInUse = false;

    public void use(){
        isInUse = true;
    }
    public void free(){
        isInUse = false;
    }

    public boolean isInUse(){
        return isInUse;
    }
}
