package com.example.food_basket_optimization.extractpojo.util;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FailedMapping implements ExtractedEntity {
    private final String errorMessage;
    private final String data;
    private final List<Object> args;
}
