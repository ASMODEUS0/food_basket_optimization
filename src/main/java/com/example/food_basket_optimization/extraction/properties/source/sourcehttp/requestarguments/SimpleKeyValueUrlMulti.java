package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments;

import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class SimpleKeyValueUrlMulti implements KeyValueUrlMultiProperties {

    private final String key;
    private final String value;
    @Override
    public List<KeyValueUrlProperties> multiply() {
        return List.of(new KeyValueUrlBasic(key, value));
    }
}
