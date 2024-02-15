package com.example.food_basket_optimization.importer.parser.parsedproperties.url;

import java.util.List;

/**
 * Represent url key value properties
 */
public interface KeyValueUrlProperty extends KeyValueContract<String, String>{

    /**
     * Multiply property depending on subclass pattern
     * @return propagated properties witch cast to KeyValueUrlBasic
     */
    List<? extends KeyValueUrlProperty> multiply();
}
