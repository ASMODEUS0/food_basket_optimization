package com.example.food_basket_optimization.extraction.properties.mapping.jsonmap;

import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.annotation.JsonCollection;
import com.example.food_basket_optimization.extractpojo.util.FailedMapping;
import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.Mapper;
import com.example.food_basket_optimization.extraction.properties.mapping.MapProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JsonMapper implements Mapper {

    @Override
    public List<ExtractedEntity> map(List<? extends MapProperty> properties) {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return properties.stream().map(this::map).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public List<ExtractedEntity> map(MapProperty property) {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Class<? extends ExtractedEntity> classToMap = property.getClassToMap();
        String transformedData = JsonDataTransformer.transformDataToClass(property.getData(), classToMap);

        try {
            List<ExtractedEntity> result;
            if (classToMap.isAnnotationPresent(JsonCollection.class)) {
                CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, classToMap);
                result = om.readValue(transformedData, collectionType);
            } else {
                result = List.of(om.readValue(transformedData, property.getClassToMap()));
            }
            return injectReferenceEntities(result, new ArrayList<>(property.getReferenceEntities()));
        } catch (JsonProcessingException e) {
            log.warn("Fail to map extracted source to: " + property.getClassToMap());
            return List.of(new FailedMapping(e.getMessage(), property.getData(), null));
        }
    }


    public List<ExtractedEntity> injectReferenceEntities(List<ExtractedEntity> mappedEntities, List<ExtractedEntity> referencedEntities){
        return mappedEntities.stream().map(entity -> injectReferenceEntity(entity, referencedEntities)).toList();
    }


    public ExtractedEntity injectReferenceEntity(ExtractedEntity entity, List<ExtractedEntity> referencedEntities){
        if(referencedEntities.isEmpty()){
            return entity;
        }
        List<Field> fields = Arrays.stream(entity.getClass().getDeclaredFields()).toList();

        fields.forEach(field -> {
            Optional<ExtractedEntity> mayBeRefEntityOfFieldType = referencedEntities.stream().filter(refEntity -> refEntity.getClass() == field.getType()).findFirst();
            mayBeRefEntityOfFieldType.ifPresent(entityOfFieldType-> {
                try {
                    field.setAccessible(true);
                    field.set(entity, entityOfFieldType);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
        });
        return entity;
    }
}
