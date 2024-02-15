package com.example.food_basket_optimization.importer.parser.parsedproperties.url;

import java.util.List;
import java.util.Map;

public class KeyValueIterableRef implements IterableKeyValue<String, String>{
    private final String key;
    private final String value;
    private final Map<Class<?>, List<?>> context;

    public KeyValueIterableRef(String key, String value, Map<Class<?>, List<?>> context) {
        this.key = key;
        this.value = value;
        this.context = context;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public List<KeyValueContract<String, String>> iterate() {
        return null;
    }
}
