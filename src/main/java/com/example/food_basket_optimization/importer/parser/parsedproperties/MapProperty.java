package com.example.food_basket_optimization.importer.parser.parsedproperties;

import java.util.List;
import java.util.Map;

public interface MapProperty {
    String getData();
    Class<?> getClassToParse();
    Map<String , Object> getReferenceEntities();

}
