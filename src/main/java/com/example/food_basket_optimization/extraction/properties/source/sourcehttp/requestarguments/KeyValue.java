package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KeyValue implements KeyValueContract<String, String>{

    private final String key;
    private final String value;

    @Override
    public String key() {
        return key;
    }

    @Override
    public String value() {
        return value;
    }
}
