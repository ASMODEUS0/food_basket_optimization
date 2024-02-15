package com.example.food_basket_optimization.importer.parser.parsedproperties;

import java.util.List;
import java.util.Optional;

public class JsonMapper implements Mapper {


//    @Override
    public Optional<List<Object>> map(List<String> data, Class<?> classToParse) {
        return Optional.empty();
    }

    @Override
    public List<Object> map(List<? extends MapProperty> properties) {
        return null;
    }
}
