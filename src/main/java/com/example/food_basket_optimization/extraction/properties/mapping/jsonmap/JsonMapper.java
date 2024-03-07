package com.example.food_basket_optimization.extraction.properties.mapping.jsonmap;

import com.example.food_basket_optimization.extractedentity.DiksiCityExtr;
import com.example.food_basket_optimization.extractedentity.util.FailedMapping;
import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.Mapper;
import com.example.food_basket_optimization.extraction.properties.mapping.MapProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.asm.TypeReference;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Component
@Slf4j
public class JsonMapper implements Mapper {

    @Override
    public List<ExtractedEntity> map(List<? extends MapProperty> properties) {
        ObjectMapper om = new ObjectMapper();

        return properties.stream().map(property -> {
                    try {
                        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, property.getClassToMap());
                        return om.<List<ExtractedEntity>>readValue(property.getData(), collectionType);
                    } catch (IOException e) {
                        FailedMapping failedMapping = new FailedMapping(e.getMessage(),
                                property.getData(),
                                List.of(property.getClassToMap()));
                        log.warn("Json mapper can't map data to entity with class: " + property.getClassToMap());
                        return new ArrayList<ExtractedEntity>(List.of(failedMapping)
                        );
                    }
                }).flatMap(Collection::stream)
                .toList();
    }
}
