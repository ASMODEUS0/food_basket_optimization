package com.example.food_basket_optimization.integration.extractor.parser.parsedobject;

import com.example.food_basket_optimization.extractproperties.extractedentity.lenta.LentaCityExt;
import com.example.food_basket_optimization.extraction.tool.ExtractCallable;
import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.Extractor;
import com.example.food_basket_optimization.extraction.tool.extractobject.ExtractObject;
import com.example.food_basket_optimization.extraction.properties.root.HttpJsonProperties;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.SourceHttpContract;
import com.example.food_basket_optimization.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@IT
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@RequiredArgsConstructor
class ParsedObjectIT {
//
//
//    private final ExecutorService threadPool = Executors.newCachedThreadPool();
//    private final HttpJsonProperties lentaStoresParsedProperties;
//    private final Extractor extractor;
//
//    private final LentaCityExt LONDON_LENTA_CITY = new LentaCityExt("spb", "SAINT-P");
//    private final LentaCityExt MOSCOW_LENTA_CITY = new LentaCityExt("msk", "MOSCOW");
//
//    @BeforeAll
//    void prepare(){
//        extractor.getExtractContext().put(LentaCityExt.class, List.of(LONDON_LENTA_CITY, MOSCOW_LENTA_CITY));
//    }
//
//
//    @Test
//    void testExtractedObject() throws InterruptedException, ExecutionException {
//
//        ExtractObject<ResolvableSource<SourceHttpContract>> lentaStoreExtractObject = new ExtractObject<>(extractor, lentaStoresParsedProperties);
//        ExtractCallable extractCallable = new ExtractCallable(lentaStoreExtractObject);
//        Future<List<? extends ExtractedEntity>> submit = threadPool.submit(extractCallable);
//
//        while (!submit.isDone()){
//            Thread.sleep(100);
//        }
//
//        List<? extends ExtractedEntity> extracteds = submit.get();
//
//
//
//    }
//
//    @Test
//    void parseIsPossible() {
//    }
}