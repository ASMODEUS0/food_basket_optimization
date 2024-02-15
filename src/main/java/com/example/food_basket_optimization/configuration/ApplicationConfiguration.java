package com.example.food_basket_optimization.configuration;


import com.example.food_basket_optimization.importer.ImportConfiguration;
import com.example.food_basket_optimization.importer.parser.parsedproperties.*;
import com.example.food_basket_optimization.importer.parser.parsedproperties.url.HttpMethod;
import com.example.food_basket_optimization.importer.parser.parsedproperties.url.KeyValueUrlBasicContextual;
import com.example.food_basket_optimization.importer.parser.parsedproperties.url.KeyValueUrlContextual;
import com.example.food_basket_optimization.importer.parser.parsedproperties.url.KeyValueUrlIterable;
import com.example.food_basket_optimization.pojo.DiksiCity;
import com.example.food_basket_optimization.pojo.DiksiProduct;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Configuration
public class ApplicationConfiguration {


    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster =
                new SimpleApplicationEventMulticaster();

        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }

    @Bean(name = "config")
    public ImportConfiguration configuration() {
        return new ImportConfiguration(propertiesHttp());
    }


    @SneakyThrows
    @Bean()
    public List<ParsedProperties> propertiesHttp() {
        ArrayList<ParsedProperties> result = new ArrayList<>();
        SourceHttp citySource = new SourceHttp(URI.create("https://dixy.ru/catalog/"),
                HttpMethod.GET, "",
                new ArrayList<>(),
                new ArrayList<>());

        HttpHtmlProperties cityProperties = new HttpHtmlProperties(citySource, DiksiCity.class, importContext(), htmlMapper());



        KeyValueUrlContextual header = new KeyValueUrlContextual("BITRIX_SM_PROJECT_REGION_IDREF(com.example.food_basket_optimization.pojo.DiksiCity|id)", "Cookie",importContext(), DiksiCity.class );
        KeyValueUrlIterable param = new KeyValueUrlIterable("PAGEN_1=", "", 1, 3);
        SourceHttp productSource = new SourceHttp(URI.create("https://dixy.ru/catalog/"), HttpMethod.GET, "", List.of(param), List.of(header));

        HttpHtmlProperties productProperties = new HttpHtmlProperties(productSource, DiksiProduct.class, importContext(), htmlMapper());

        result.add(cityProperties);
        result.add(productProperties);
        return result;
    }


    @Bean
    public HttpSourceResolver httpSourceResolver() {
        return new HttpSourceResolver();
    }



    @Bean
    public ConcurrentMap<Class<?>, List<?>> importContext() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    public HtmlMapper htmlMapper() {
        return new HtmlMapper();
    }


}
