package com.example.food_basket_optimization.extraction.properties.mapping.htmlmap;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.MapProperty;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class HtmlMapProperty implements MapProperty {

    private final String htmlStr;
    private final Class<? extends ExtractedEntity> classToParse;
    private final List<? extends ExtractedEntity> referenceEntities;

    @Override
    public String getData() {
        return htmlStr;
    }

    @Override
    public Class<? extends ExtractedEntity> getClassToMap() {
        return classToParse;
    }

    @Override
    public List<? extends ExtractedEntity> getReferenceEntities() {
        return referenceEntities;
    }


}
