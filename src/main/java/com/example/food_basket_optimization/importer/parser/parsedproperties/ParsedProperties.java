package com.example.food_basket_optimization.importer.parser.parsedproperties;

/**
 * Describes an object that displays properties that can be used to take control
 * over the parsing of an entity. Parsing occurs entities of the same type
 */
public interface ParsedProperties<T extends ParsedSourceContract> {
    Class<?> getClassToParse();
    Mapper getMapper();
    T getParsedSource();
    SourceResolverContract<T> getSourceResolver();
    Boolean parseIsPossible();
}
