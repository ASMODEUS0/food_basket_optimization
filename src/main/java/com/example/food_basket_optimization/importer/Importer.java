package com.example.food_basket_optimization.importer;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ExtractedEntityMappedObject;
import com.example.food_basket_optimization.extraction.Extractor;
import com.example.food_basket_optimization.extraction.mapper.ExtractedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;


/**
 * service responsible for parsing a resource and adding data from this resource to the database
 */
@RequiredArgsConstructor
@Component
public class Importer {

    private final Extractor extractor;
    private final ExtractedMapper extractedMapper;



    public void importAll(){

        List<Future<List<? extends ExtractedEntity>>> extractedFutures = extractor.extract();
        while(true){
            boolean extractionContinues = true;
            for(Future<List<? extends ExtractedEntity>> extractFut: extractedFutures){
                if (!extractFut.isDone()) {
                    extractionContinues = false;
                }
            }
            if(extractionContinues){
                break;
            }
        }

        List<? extends List<? extends ExtractedEntity>> extractedObjects = extractedFutures.stream().map(fut -> {
            try {
                return (List<? extends ExtractedEntity>) fut.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).toList();

        List<Object> collect = extractedObjects.stream().flatMap(Collection::stream).flatMap(object -> {
            if (object instanceof ExtractedEntityMappedObject<?>) {
                return Stream.of(extractedMapper.map((ExtractedEntityMappedObject<?>) object));
            }
            return Stream.empty();
        }).toList();

        ExtractedEntity extracted = extractedObjects.get(0).get(0);

//        Object map = extractedMapper.map(extracted);


    }


}
