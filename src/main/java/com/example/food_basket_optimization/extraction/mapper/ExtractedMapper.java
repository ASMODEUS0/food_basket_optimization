package com.example.food_basket_optimization.extraction.mapper;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ExtractedEntityMappedObject;
import com.example.food_basket_optimization.extraction.Extractor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ExtractedMapper {
    private final Extractor extractor;

    public ExtractedMapper(Extractor extractor) {
        this.extractor = extractor;
    }


    public Object map(ExtractedEntityMappedObject<?> extractedObject){

        ArrayList<Object> mapArgs = new ArrayList<>();
        try {
          Method  mapMethod = extractedObject.getClass().getMethod("map", Object[].class);

          if(mapMethod.isAnnotationPresent(MapByEquals.class)) {
              MapByEquals annotation = mapMethod.getAnnotation(MapByEquals.class);
              mapByEquals(annotation, extractedObject).ifPresent(mapArgs::add);
          }

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return extractedObject.map(mapArgs);
    }


    private Optional<?> mapByEquals(MapByEquals annotation, ExtractedEntityMappedObject<?> extractedObject){
        if(extractor.getExtractContext().containsKey(annotation.unionClazz())){
            try {
                List<? extends ExtractedEntity> extractedReferenceObjects = extractor.getExtractContext().get(annotation.unionClazz());
                Field declaredField = extractedObject.getClass().getDeclaredField(annotation.ownField());
                declaredField.setAccessible(true);
                Object o = declaredField.get(extractedObject);
                for(ExtractedEntity extractedReference: extractedReferenceObjects){
                    Field declaredRefrenceField = extractedReference.getClass().getDeclaredField(annotation.unionField());
                    declaredRefrenceField.setAccessible(true);
                    Object o1 = declaredRefrenceField.get(extractedReference);
                    if(o1.equals(o)){
                        return Optional.of(extractedReference);
                    }
                }

            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }else {
            throw new IllegalArgumentException("Context has no extarcted object with ");
        }


        return Optional.empty();
    }
}
