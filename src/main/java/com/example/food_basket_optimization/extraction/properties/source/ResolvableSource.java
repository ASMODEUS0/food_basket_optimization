package com.example.food_basket_optimization.extraction.properties.source;

import com.example.food_basket_optimization.extraction.ReferencedExtraction;

import java.util.List;

public interface ResolvableSource<S> extends ReferencedExtraction {
    List<S> resolve();
}
