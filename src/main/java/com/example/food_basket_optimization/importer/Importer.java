package com.example.food_basket_optimization.importer;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ExtractedEntityMappedObject;
import com.example.food_basket_optimization.extraction.Extractor;
import com.example.food_basket_optimization.extraction.mapper.ExtractedMapper;
import com.example.food_basket_optimization.util.HibernateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;


/**
 * Service responsible for parsing a resource and adding data from this resource to the database
 */
@RequiredArgsConstructor
@Component
public class Importer {

    private final Extractor extractor;
    private final ExtractedMapper extractedMapper;


    public void importAll() {

        List<Future<List<? extends ExtractedEntity>>> extractedFutures = extractor.extract();

        while (true) {
            if (extractedFutures.stream().filter(Future::isDone).count() == extractedFutures.size()) {
                break;
            }
        }


        List<? extends ExtractedEntityMappedObject<?>> extractedEntityMappedObjects = extractedFutures.stream().map(fut -> {
            try {
                return fut.get().stream()
                        .flatMap(entity -> entity instanceof ExtractedEntityMappedObject<?> mappedEntity ? Stream.of(mappedEntity) : Stream.empty())
                        .toList();

            } catch (InterruptedException | ExecutionException e) {

                throw new RuntimeException(e);
            }
        }).flatMap(Collection::stream).toList();


        save(extractedEntityMappedObjects);


        System.out.println("");



//        ExtractedEntity extracted = extractedObjects.get(0).get(0);

//        Object map = extractedMapper.map(extracted);


    }


    private void save(List<? extends ExtractedEntityMappedObject<?>> objects){
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
            Session session = sessionFactory.openSession()){
            session.beginTransaction();

            objects.forEach(object ->{
                Object mappedObject = object.map();
                session.persist(mappedObject);
            });

            session.getTransaction().commit();
        }


    }


}
