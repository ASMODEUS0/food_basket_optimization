package com.example.food_basket_optimization.integration.extractor;

import com.example.food_basket_optimization.extractproperties.extractedentity.lenta.LentaCityExt;
import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.Extractor;
import com.example.food_basket_optimization.extraction.properties.root.HttpJsonProperties;
import com.example.food_basket_optimization.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@IT
@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExtractorTest {



    private final Extractor extractor;
    private final HttpJsonProperties lentaStoresParsedProperties;
    private final ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext;
    private final LentaCityExt LONDON_LENTA_CITY = new LentaCityExt("spb", "SAINT-P");
    private final LentaCityExt MOSCOW_LENTA_CITY = new LentaCityExt("msk", "MOSCOW");

    @BeforeAll
    void prepare(){
        extractContext.put(LentaCityExt.class, List.of(LONDON_LENTA_CITY, MOSCOW_LENTA_CITY));
    }


    @Test
    void test() throws InterruptedException, ExecutionException {



        Future<List<? extends ExtractedEntity>> extractFut = extractor.extractEntityWith(lentaStoresParsedProperties);

        while(!extractFut.isDone()){
            Thread.sleep(100);
        }
        List<? extends ExtractedEntity> extractedEntities = extractFut.get();

        Assertions.assertThat(extractedEntities).isNotEmpty();
    }

}