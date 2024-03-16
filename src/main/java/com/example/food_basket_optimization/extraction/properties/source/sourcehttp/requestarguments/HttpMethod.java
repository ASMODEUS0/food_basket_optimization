package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments;

import lombok.Getter;

@Getter
public enum HttpMethod {

    GET("get"), POST("post");

    private final String methodName;

    HttpMethod(String methodName){
      this.methodName = methodName;
    }

}
