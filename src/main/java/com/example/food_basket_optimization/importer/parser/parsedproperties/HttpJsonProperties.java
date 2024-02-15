package com.example.food_basket_optimization.importer.parser.parsedproperties;


import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class HttpJsonProperties implements ParsedProperties {


   private final SourceHttp source;
   private final Class<?> classToParse;
   private final JsonMapper mapper;


    @Override
    public ParsedSourceContract getParsedSource() {
        return source;
    }

    @Override
    public Class<?> getClassToParse() {
        return classToParse;
    }

    @Override
    public Mapper getMapper() {
        return mapper;
    }

    @Override
    public SourceResolverContract getSourceResolver() {
        return null;
    }

    @Override
    public Boolean parseIsPossible() {
        return null;
    }
}
