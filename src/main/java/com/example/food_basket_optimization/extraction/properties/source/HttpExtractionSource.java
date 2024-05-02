package com.example.food_basket_optimization.extraction.properties.source;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.source.ExtractionSource;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.SourceHttp;

import java.util.List;

public record HttpExtractionSource(SourceHttp sourceHttp,
                                   List<ExtractedEntity> referencesEntities) implements ExtractionSource {

}
