package com.example.food_basket_optimization.extraction.service.request.requesthandler;

import com.example.food_basket_optimization.extraction.service.request.components.DefaultRequestComponents;

import java.util.List;

public interface RequestHandler {

    List<String> doRequests(List<DefaultRequestComponents> componentsList);


}
