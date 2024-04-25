package com.example.food_basket_optimization.extraction.service.request.processing;

import org.apache.hc.core5.http.Header;

import java.util.List;

@FunctionalInterface
public interface ProcessingProxyCookie {

    List<Header> processing(String address, Integer port, String login, String password) ;
}
