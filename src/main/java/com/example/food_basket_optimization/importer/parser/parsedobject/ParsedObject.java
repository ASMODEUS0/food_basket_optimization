package com.example.food_basket_optimization.importer.parser.parsedobject;


//Интерфейс обозначающий, что класс используется для парсинга какой-то сущности.
public interface ParsedObject {

    Class<?> getClassToParse();

    String getData();



}
