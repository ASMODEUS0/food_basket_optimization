package com.example.food_basket_optimization.extractpojo.extractedentity.magnit.body;

import com.example.food_basket_optimization.extraction.properties.SimplePropertyAbs;

public class Filter extends SimplePropertyAbs<String> {
        public String id;
        public Range range;

        @Override
        public String property() {
                throw new IllegalArgumentException();
        }
}
