package com.example.food_basket_optimization.importer.parser.parsedobject;

import com.example.food_basket_optimization.importer.parser.parsedobject.url.Param;
import com.example.food_basket_optimization.importer.parser.parsedobject.url.Header;
import com.example.food_basket_optimization.importer.parser.parsedobject.url.HttpMethod;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@Getter
public abstract class HttpObject {


   private final List<Header> headers;

   private final URI uri;

   private final HttpMethod httpMethod;

   private final String body;

   private final List<Param> params;



}
