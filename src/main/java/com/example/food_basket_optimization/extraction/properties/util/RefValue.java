package com.example.food_basket_optimization.extraction.properties.util;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import lombok.*;

/**
 * Represent value witch can point on some contextual value;
 */
@Setter
@EqualsAndHashCode
@Getter
public class RefValue {
    private final Class<? extends ExtractedEntity> refClass ;
    private final String fieldName;

    public RefValue(Class<? extends ExtractedEntity> refClass, String fieldName) {
        this.refClass = refClass;
        this.fieldName = fieldName;
    }


    @Override
    public String toString() {
        return "{\"refClass\":\" " + refClass.getName() + "\"," +
               " \"fieldName\":\"" + fieldName + "\"}";
    }

}
