package com.example.food_basket_optimization.extraction.service.request.components;

import java.net.URI;
import java.net.URL;
import java.util.List;

public interface RequestComponents {
   URI getUri();
   List<String> getHeaders();
}
