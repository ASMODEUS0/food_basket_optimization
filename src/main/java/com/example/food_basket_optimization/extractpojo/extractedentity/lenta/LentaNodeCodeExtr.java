package com.example.food_basket_optimization.extractpojo.extractedentity.lenta;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import lombok.Getter;

@Getter
public class LentaNodeCodeExtr implements ExtractedEntity {
    private final String nodeCode;

    public LentaNodeCodeExtr(String nodeCode) {
        this.nodeCode = nodeCode;
    }
}
