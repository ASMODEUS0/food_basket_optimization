package com.example.food_basket_optimization.extraction.tool.extractobject;

import com.example.food_basket_optimization.extraction.ExtractedEntity;

import java.util.List;

public interface ExtractObjectContract {

    Boolean parseIsPossible();
    List<? extends ExtractedEntity> parse();
}
