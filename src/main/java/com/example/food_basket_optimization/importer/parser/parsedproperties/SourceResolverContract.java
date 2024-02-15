package com.example.food_basket_optimization.importer.parser.parsedproperties;

import java.util.List;

public interface SourceResolverContract<T extends ParsedSourceContract> {
    List<? extends MapProperty> getData(T source, Class<?> classToParse);
}
