package com.example.food_basket_optimization.extraction.properties.mapping.jsonmap;

import com.example.food_basket_optimization.extractproperties.util.FailedMapping;
import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.Mapper;
import com.example.food_basket_optimization.extraction.properties.mapping.MapProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Slf4j
public class JsonMapper implements Mapper {

    @Override
    public List<ExtractedEntity> map(List<? extends MapProperty> properties) {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return properties.stream().map(property -> {
                    Class<? extends ExtractedEntity> classToMap = property.getClassToMap();
                    try {
                        String transformedData = JsonDataTransformer.transformDataToClass(property.getData(), classToMap);
                        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, classToMap);
                        return om.<List<ExtractedEntity>>readValue(transformedData, collectionType);
                    } catch (IOException e) {
                        FailedMapping failedMapping = new FailedMapping(e.getMessage(),
                                property.getData(),
                                List.of(classToMap));
                        log.warn("Json mapper can't map data to entity with class: " + classToMap);
                        return new ArrayList<ExtractedEntity>(List.of(failedMapping)
                        );
                    }
                }).flatMap(Collection::stream)
                .toList();
    }
}
