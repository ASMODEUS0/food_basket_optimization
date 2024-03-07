package com.example.food_basket_optimization.configuration;


import com.example.food_basket_optimization.extractedentity.lenta.LentaCityExt;
import com.example.food_basket_optimization.extractedentity.lenta.LentaStoreExt;
import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.JsonMapper;
import com.example.food_basket_optimization.extraction.properties.root.HttpJsonProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.ResolvableSourceHttpProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.HttpMethod;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlContextualPathProperties;
import com.example.food_basket_optimization.extraction.properties.sourceresolver.SourceHttpResolver;
import com.example.food_basket_optimization.extraction.properties.util.RefValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Configuration
public class ApplicationConfiguration {


    @Bean
    public ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext() {
        return new ConcurrentHashMap<>();
    }


    @Bean
    public HttpJsonProperties lentaStoresParsedProperties(JsonMapper jsonMapper, SourceHttpResolver sourceHttpResolver) {
       return  new HttpJsonProperties(lentaStoresSourceHttpResolvableProperties(),
                LentaStoreExt.class,
                jsonMapper,
                sourceHttpResolver
        );
    }

    @Bean
    public ResolvableSourceHttpProperties lentaStoresSourceHttpResolvableProperties() {
        return new ResolvableSourceHttpProperties(new ArrayList<>(),
                new ArrayList<>(),
                HttpMethod.GET,
                lentaStoresUrlProperties(),
                "");
    }

    @Bean
    public UrlContextualPathProperties lentaStoresUrlProperties() {
        return new UrlContextualPathProperties("https",
                List.of("api", "v2", "cities", new RefValue(LentaCityExt.class, "id").toString(), "stores"),
                "lenta.com",
                extractContext());
    }


}
