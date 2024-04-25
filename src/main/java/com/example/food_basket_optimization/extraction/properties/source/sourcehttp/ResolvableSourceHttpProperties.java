package com.example.food_basket_optimization.extraction.properties.source.sourcehttp;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.Properties;
import com.example.food_basket_optimization.extraction.properties.base.multi.MultiString;
import com.example.food_basket_optimization.extraction.properties.base.simple.SimpleString;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.*;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlMultiProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlProperties;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;
import com.example.food_basket_optimization.extraction.properties.util.ExtractUtil;
import com.example.food_basket_optimization.extraction.properties.util.MultiplierUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ResolvableSourceHttpProperties implements ResolvableSource<SourceHttp> {



    private final List<Class<? extends ExtractedEntity>> refClasses = new ArrayList<>();
    private final KeyValueUrlMultiUnionProperties params;
    private final KeyValueUrlMultiUnionProperties headers;
    private final HttpMethod method;
    private final UrlMultiProperties urlProperty;
    private final MultiString body;


    public ResolvableSourceHttpProperties(KeyValueUrlMultiUnionProperties params,
                                          KeyValueUrlMultiUnionProperties headers,
                                          HttpMethod method,
                                          UrlMultiProperties urlProperty,
                                          MultiString body) {
        this.body = body;
        this.urlProperty = urlProperty;
        this.method = method;
        this.headers = headers;
        this.params = params;

//        detects ref values
        refClasses.addAll(ExtractUtil.detectObjectReferences(params));
        refClasses.addAll(ExtractUtil.detectObjectReferences(headers));
        refClasses.addAll(ExtractUtil.detectObjectReferences(urlProperty));
        refClasses.addAll(ExtractUtil.detectObjectReferences(body));

        System.out.println("");

    }


    @Override
    public List<SourceHttp> resolve() {


        List<List<KeyValueUrlProperties>> paramsMultiplied = params.multiply();
        List<List<KeyValueUrlProperties>> headersMultiplied = headers.multiply();
        List<HttpMethod> listedMethod = List.of(method);
        List<UrlProperties> urlMultiplied = urlProperty.multiply();
        List<SimpleString> bodyMultiplied = body.multiply();





        List<List<Object>> multipliedObjectsParams = MultiplierUtil.directProduct(List.of(urlMultiplied, listedMethod, bodyMultiplied, paramsMultiplied, headersMultiplied));


        List<List<Object>> mopWithRefValues = multipliedObjectsParams.stream().map(objectParams -> {

            List<Object> result = new ArrayList<>(objectParams);
            List<ExtractedEntity> refValues = objectParams.stream().flatMap(objectParam -> {
                if (objectParam instanceof Properties paramProperties) {
                    return paramProperties.getReferenceEntities().stream();
                }
                return Stream.empty();
            }).toList();
            result.add(refValues);
            return result;
        }).toList();


        List<SourceHttp> objectsFromMultipliedParams = MultiplierUtil.createObjectsFromMultipliedParams(SourceHttp.class, mopWithRefValues);
        return objectsFromMultipliedParams;

    }

    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return refClasses;
    }
}
