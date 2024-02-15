package com.example.food_basket_optimization.importer.parser.parsedproperties.url;

import lombok.Getter;


public class KeyValueURL implements KeyValueContract<String, String> {
   private final String key;
   private final String value;

    public KeyValueURL(String key, String value) {
        this.key = key;
        this.value = value;
    }
    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

}
