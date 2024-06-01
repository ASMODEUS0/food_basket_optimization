package com.example.food_basket_optimization.extraction.properties.sourceresolver;


import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.MapProperty;
import com.example.food_basket_optimization.extraction.properties.mapping.htmlmap.HtmlMapProperty;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.HttpExtractionSource;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.SourceHttp;
import com.example.food_basket_optimization.extraction.service.request.components.DefaultRequestComponents;
import com.example.food_basket_optimization.extraction.service.request.requesthandler.ProxyRequestHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ContentType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Scope(value = "prototype")
@Component()
public class SourceHttpResolver implements SourceResolverContract<ResolvableSource<HttpExtractionSource>> {


    private ProxyRequestHandler requestHandler;


    public SourceHttpResolver(ProxyRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public List<MapProperty> getData(ResolvableSource<HttpExtractionSource> resolvableSource, Class<? extends ExtractedEntity> classToParse) {

        List<HttpExtractionSource> resolvedSources = resolvableSource.resolve();

        List<DefaultRequestComponents> requestComponents = resolvedSources.stream().map(extractionSource -> {
            SourceHttp sourceHttp = extractionSource.sourceHttp();

            List<String> headers = sourceHttp.getHeaders().stream().flatMap(header -> Stream.of(header.key(), header.value())).toList();
            return new DefaultRequestComponents(sourceHttp.getUri(),
                    headers,
                    sourceHttp.getMethod(),
                    new ByteArrayInputStream(sourceHttp.getBody().getBytes()),
                    ContentType.APPLICATION_JSON);
        }).toList();

        log.info("Do request for resolved sources of size:" + resolvedSources.size());

        long l = System.currentTimeMillis();
        List<String> responses = requestHandler.doRequests(requestComponents);
        long time = System.currentTimeMillis() - l;
        log.info("Execution time of request: " + time * 0.001 + " seconds");


        List<MapProperty> mapProperties = new ArrayList<>();

        for (int i = 0; i < responses.size(); i++) {
            mapProperties.add(new HtmlMapProperty(responses.get(i), classToParse, resolvedSources.get(i).referencesEntities()));
        }
        return mapProperties;
    }


    public void setRequestHandler(ProxyRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }


}
