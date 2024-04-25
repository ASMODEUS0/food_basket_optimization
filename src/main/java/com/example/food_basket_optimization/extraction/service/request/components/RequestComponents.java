package com.example.food_basket_optimization.extraction.service.request.components;

import java.net.URI;
import java.net.URL;
import java.util.List;

public interface RequestComponents {
   URL getUrl();
   List<String> getParams();
   List<String> getHeaders();
   URI getURI();
}
