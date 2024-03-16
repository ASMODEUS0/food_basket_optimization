package com.example.food_basket_optimization.extraction.properties.sourceresolver;


import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.MapProperty;
import com.example.food_basket_optimization.extraction.properties.mapping.htmlmap.HtmlMapProperty;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.SourceHttp;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.SourceHttpContract;
import com.example.food_basket_optimization.extraction.properties.util.RequestUtill;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class SourceHttpResolver implements SourceResolverContract<ResolvableSource<SourceHttp>> {

    private final HttpClient client;


    public SourceHttpResolver() {
        client = HttpClient.newHttpClient();
    }

    // TODO: 04.03.2024 request executor with fork tasks
    @Override
    public List<MapProperty> getData(ResolvableSource<SourceHttp> resolvableSource, Class<? extends ExtractedEntity> classToParse) {
        List<SourceHttp> resolvedSources = resolvableSource.resolve();

        List<MapProperty> mapProperties = new ArrayList<>();
        for (SourceHttpContract source : resolvedSources) {

            Stream.of(source).map(this::createRequestFrom).map(request -> {
                        try {
                            return client.send(request, HttpResponse.BodyHandlers.ofString())
                                    .body();
                        } catch (IOException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }).map(data -> new HtmlMapProperty(data, classToParse, source.getReferenceEntities()))
                    .findFirst()
                    .ifPresent(mapProperties::add);

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


}
