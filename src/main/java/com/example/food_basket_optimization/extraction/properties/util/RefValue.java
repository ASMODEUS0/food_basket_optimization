package com.example.food_basket_optimization.extraction.properties.util;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import lombok.*;

/**
 * Represent value witch can point on some contextual value;
 */
@AllArgsConstructor
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class RefValue {
    public Class<? extends ExtractedEntity> refClass;
    public String fieldName;


    @Override
    public String toString() {
        return "{\"refClass\":\" " + refClass.getName() + "\"," +
               " \"fieldName\":\"" + fieldName + "\"}";
    }
}
