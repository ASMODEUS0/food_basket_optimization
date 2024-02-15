package com.example.food_basket_optimization.importer.parser.parsedproperties;


import com.example.food_basket_optimization.importer.parser.parsedproperties.url.*;
import com.example.food_basket_optimization.importer.parser.service.RequestService;
import com.example.food_basket_optimization.importer.parser.util.RequestUtill;
import com.example.food_basket_optimization.pojo.DiksiCity;
import com.example.food_basket_optimization.pojo.DiksiProduct;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class HttpSourceResolver implements SourceResolverContract<SourceHttp> {


    @Override
    public List<? extends MapProperty> getData(SourceHttp source, Class<?> classToParse) {


        List<SourceHttp> sourcesMultiplied = multiplySource(source);


        return sourcesMultiplied.stream().map(sourceCur -> {

            HttpRequest httpRequest = RequestUtill.convertFrom(sourceCur);
            String htmlStr = RequestService.doRequest(httpRequest);
            ArrayList<KeyValueUrlProperty> keyValueUrlProperties = new ArrayList<>(sourceCur.getHeaders());
            keyValueUrlProperties.addAll(sourceCur.getParams());

            Map<String, Object> referenceEntities = new HashMap<>();

            keyValueUrlProperties.forEach(property -> {
                if (property instanceof KeyValueUrlBasicContextual) {
                    Object referenceEntity = ((KeyValueUrlBasicContextual) property).getReferenceEntity();
                    referenceEntities.put(property.getKey(), referenceEntity);
                }
            });

            return new HtmlMapProperty(htmlStr, classToParse, referenceEntities);

        }).toList();


    }





    private List<SourceHttp> multiplySource(SourceHttp source) {

        ArrayList<SourceHttp> sources = new ArrayList<>();
        sources.add(source);


        List<KeyValueUrlProperty> headers = source.getHeaders();
        List<KeyValueUrlProperty> params = source.getParams();
        headers.forEach(header -> {
            List<? extends KeyValueUrlProperty> multipliedHeaders = header.multiply();
            List<SourceHttp> multipliedSources = applyMultiplication(sources, MultiplyFieldType.HEADER, header, multipliedHeaders);
            sources.clear();
            sources.addAll(multipliedSources);
        });

        params.forEach(param -> {
            List<? extends KeyValueUrlProperty> multipliedParams = param.multiply();
            List<SourceHttp> multipliedSources = applyMultiplication(sources, MultiplyFieldType.PARAM, param, multipliedParams);
            sources.clear();
            sources.addAll(multipliedSources);
        });


        return sources;
    }

    /**
     * @param sources          Sources that has initial field
     * @param fieldType        Use for creation a new SourceHttp, describe witch field is need to be multiplied
     * @param initialField     Field that will be multiplied in sources
     * @param multipliedFields Result of multiplication of initialField
     * @return List of multiplied sources
     */
    private List<SourceHttp> applyMultiplication(List<SourceHttp> sources, MultiplyFieldType fieldType, KeyValueUrlProperty initialField, List<? extends KeyValueUrlProperty> multipliedFields) {

        ArrayList<SourceHttp> sourcesMultiplied = new ArrayList<>(sources);

        sources.forEach(source -> {
            //KeyValue witch will be the same as source field except multiplied field
            ArrayList<KeyValueUrlProperty> baseKeyValueConfig;
            switch (fieldType) {
                case PARAM -> {
                    baseKeyValueConfig = new ArrayList<>(source.getParams());
                    baseKeyValueConfig.remove(initialField);
                    List<SourceHttp> result = applyMultiplicationParam(source, multipliedFields, baseKeyValueConfig);
                    sourcesMultiplied.remove(source);
//                    sourcesMultiplied.clear();
                    sourcesMultiplied.addAll(result);
                }
                case HEADER -> {
                    baseKeyValueConfig = new ArrayList<>(source.getHeaders());
                    baseKeyValueConfig.remove(initialField);
                    List<SourceHttp> result = applyMultiplicationHeader(source, multipliedFields, baseKeyValueConfig);
                    sourcesMultiplied.remove(source);
//                    sourcesMultiplied.clear();
                    sourcesMultiplied.addAll(result);
                }

            }
        });

        return sourcesMultiplied;
    }

    private List<SourceHttp> applyMultiplicationHeader(SourceHttp source, List<? extends KeyValueUrlProperty> multipliedHeaders, List<KeyValueUrlProperty> baseHeaderConfig) {
        return multipliedHeaders.stream().map(header -> {

            ArrayList<KeyValueUrlProperty> headerConfig = new ArrayList<>(baseHeaderConfig);
            headerConfig.add(header);

            return new SourceHttp(source.getUri(),
                    source.getMethod(),
                    source.getBody(),
                    source.getParams(),
                    headerConfig);
        }).toList();

    }


    private List<SourceHttp> applyMultiplicationParam(SourceHttp source, List<? extends KeyValueUrlProperty> multipliedParams, List<KeyValueUrlProperty> baseParamConfig) {
        return multipliedParams.stream().map(param -> {

            ArrayList<KeyValueUrlProperty> paramConfig = new ArrayList<>(baseParamConfig);
            paramConfig.add(param);

            return new SourceHttp(source.getUri(),
                    source.getMethod(),
                    source.getBody(),
                    paramConfig,
                    source.getHeaders());
        }).toList();

    }


    private enum MultiplyFieldType {
        PARAM, HEADER
    }


}
