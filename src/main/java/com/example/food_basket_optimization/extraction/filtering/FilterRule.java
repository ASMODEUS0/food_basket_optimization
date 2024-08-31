package com.example.food_basket_optimization.extraction.filtering;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public class FilterRule {
    private final Field field;
    private final FilterType filterType;
    private final List<Object> args;

}
