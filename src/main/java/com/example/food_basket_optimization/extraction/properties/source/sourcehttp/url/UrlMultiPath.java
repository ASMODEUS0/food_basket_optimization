package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extraction.properties.base.multi.MultiString;
import com.example.food_basket_optimization.extraction.properties.base.simple.SimpleString;
import com.example.food_basket_optimization.extraction.properties.base.simple.StringProperty;
import com.example.food_basket_optimization.extraction.properties.util.ExtractUtil;
import com.example.food_basket_optimization.extraction.properties.util.MultiplierUtil;
import com.example.food_basket_optimization.extraction.properties.util.Multiplying;

import java.util.ArrayList;
import java.util.List;

public class UrlMultiPath implements UrlMultiProperties, ReferencedExtraction {

    private final String protocol;
    private final String host;
    private final List<MultiString> pathElements;
    private final List<Class<? extends ExtractedEntity>> refClasses = new ArrayList<>();




    public UrlMultiPath(String protocol,
                        String host,
                        List<MultiString> pathElements){
        this.pathElements = pathElements;
        this.protocol = protocol;
        this.host = host;
        refClasses.addAll(ExtractUtil.detectObjectReferences(pathElements));

    }



    @Override
    public List<UrlProperties> multiply() {
        List<List<SimpleString>> multipliedPathElements = pathElements.stream().map(Multiplying::multiply).toList();
        List<List<SimpleString>> pathsElements = MultiplierUtil.resolveListedMultiplication(multipliedPathElements);


        return convertToUrlsProperties(pathsElements, protocol, host);
    }



    private List<UrlProperties> convertToUrlsProperties(List<List<SimpleString>> pathsElements, String protocol, String host){
       return pathsElements.stream().map(pathElements -> convertToUrlProperties(pathElements, protocol, host)).toList();
    }

    private UrlProperties convertToUrlProperties(List<SimpleString> pathElements, String protocol, String host){
        SimpleString pathProperty = createPathFromPathElements(pathElements);
        return new UrlBasicProperties(protocol,
                pathProperty.getProperty(),
                host,
                pathProperty.getReferenceEntities());
    }



    private StringProperty createPathFromPathElements(List<SimpleString> pathElements){

        StringBuilder pathBuilder = new StringBuilder();
        List<ExtractedEntity> refEntities = new ArrayList<>();

        pathElements.forEach(pathElement -> {
            refEntities.addAll(pathElement.getReferenceEntities());
            pathBuilder.append('/').append(pathElement.getProperty());
        });

        return new StringProperty(pathBuilder.toString(), refEntities);
    }




    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return refClasses;
    }
}
