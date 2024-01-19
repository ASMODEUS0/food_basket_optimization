package com.example.food_basket_optimization.importer;

import com.example.food_basket_optimization.importer.parser.parsedobject.HtmlParsedObjectContract;

import java.util.List;

public interface BaseResource {

    public List<? extends HtmlParsedObjectContract> getHtmlParsedObjects();
}
