package com.example.food_basket_optimization.extraction.mapper;

import com.example.food_basket_optimization.entity.BaseEntity;
import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ExtractedEntityMappedObject;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class EntityMapper {
    private final ExtractedEntityMappedObject<?> mappedEntity;

    private final List<? extends ExtractedEntity> refExtractedEntities;

    public Object map(){
        return mappedEntity.map(refExtractedEntities);
    }
}
