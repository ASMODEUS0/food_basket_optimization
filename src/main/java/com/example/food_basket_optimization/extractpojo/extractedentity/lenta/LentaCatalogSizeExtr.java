package com.example.food_basket_optimization.extractpojo.extractedentity.lenta;

import com.example.food_basket_optimization.entity.City;
import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtractedEntity;

import java.util.List;

public class LentaCatalogSizeExtr implements ReferencedExtractedEntity {
    public Integer totalCount;
    public LentaNodeCodeExtr nodeCodeExtr;
    public LentaStoreExt storeExtr;

    @Override
    public Boolean referencesIsEqual(List<ExtractedEntity> references) {
        List<ExtractedEntity> equalReferences = references.stream().filter(reference -> reference == nodeCodeExtr || reference == storeExtr).toList();
       return equalReferences.size() == 2;
    }
}
