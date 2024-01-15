package com.example.food_basket_optimization.refresh.parser.properties.htpp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;
import java.util.Map;
@RequiredArgsConstructor
@Getter
public abstract class HTTPParseProperties {


   private final List<Header> headers;

   private final URI uri;

   private final HttpMethod httpMethod;

   private final String body;



}
