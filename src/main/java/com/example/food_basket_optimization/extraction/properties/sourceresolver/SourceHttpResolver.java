package com.example.food_basket_optimization.extraction.properties.sourceresolver;


import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.MapProperty;
import com.example.food_basket_optimization.extraction.properties.mapping.htmlmap.HtmlMapProperty;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.SourceHttp;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.SourceHttpContract;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.request.RequestProperties;
import com.example.food_basket_optimization.extraction.properties.util.RequestUtill;
import com.example.food_basket_optimization.extraction.service.request.components.DefaultRequestComponents;
import com.example.food_basket_optimization.extraction.service.request.requesthandler.ProxyRequestHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ContentType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Scope(value = "prototype")
@Component()
public class SourceHttpResolver implements SourceResolverContract<ResolvableSource<SourceHttp>> {


    private RequestProperties requestProperties;
    private ProxyRequestHandler requestHandler;


    public SourceHttpResolver(ProxyRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        requestProperties = new RequestProperties();
    }

    @Override
    public List<MapProperty> getData(ResolvableSource<SourceHttp> resolvableSource, Class<? extends ExtractedEntity> classToParse) {
        List<SourceHttp> resolvedSources = resolvableSource.resolve();


        List<DefaultRequestComponents> requestComponents = resolvedSources.stream().map(sourceHttp -> {
            List<String> params = sourceHttp.getParams().stream().flatMap(param -> Stream.of(param.key(), param.value())).toList();
            List<String> headers = sourceHttp.getHeaders().stream().flatMap(header -> Stream.of(header.key(), header.value())).toList();
            return new DefaultRequestComponents(sourceHttp.getUrl().getUrl(),
                    params,
                    headers,
                    sourceHttp.getMethod(),
                    new ByteArrayInputStream(sourceHttp.getBody().getProperty().getBytes()),
                    ContentType.APPLICATION_JSON);
        }).toList();

        log.info("Do request for resolved sources of size:" + resolvedSources.size());

        long l = System.currentTimeMillis();
        List<String> responses = requestHandler.doRequests(requestComponents);
        long time = System.currentTimeMillis() - l;
        log.info("Execution time of request: " + time * 0.001 + " seconds");


        List<MapProperty> mapProperties = new ArrayList<>();

        for (int i = 0; i < responses.size(); i++) {
            mapProperties.add(new HtmlMapProperty(responses.get(i), classToParse, resolvedSources.get(i).getReferenceEntities()));
        }
        return mapProperties;
    }


    private HttpRequest createRequestFrom(SourceHttpContract source) {

        URI uri = URI.create(source.getUrl().getUrl().toString());
        URI uriWithQueryParams = RequestUtill.createUriWithQueryParams(uri, source.getParams());
        List<String> headers = source.getHeaders()
                .stream()
                .flatMap(header -> Stream.of(header.key(), header.value())).toList();


        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder(uriWithQueryParams);
        HttpRequest.Builder requestBuilderWithHeaders = RequestUtill.addHeaders(requestBuilder, headers);
        return RequestUtill.resolveHttpMethod(requestBuilderWithHeaders, source.getMethod(), source.getBody().getProperty()).build();
    }


    public void setRequestProperties(RequestProperties requestProperties) {
        this.requestProperties = requestProperties;
    }

    public void setRequestHandler(ProxyRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }


}
