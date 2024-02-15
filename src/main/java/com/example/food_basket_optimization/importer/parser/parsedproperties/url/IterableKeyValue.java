package com.example.food_basket_optimization.importer.parser.parsedproperties.url;

import java.util.List;

public interface IterableKeyValue<K, V> {
    K getKey();
    V getValue();

    List<KeyValueContract<K, V>> iterate();
}
