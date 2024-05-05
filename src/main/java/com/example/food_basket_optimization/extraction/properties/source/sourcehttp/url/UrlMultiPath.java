package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.base.multi.MultiString;
import com.example.food_basket_optimization.extraction.properties.base.simple.SimpleString;
import com.example.food_basket_optimization.extraction.properties.base.simple.StringProperty;
import com.example.food_basket_optimization.extraction.properties.util.MultiplierUtil;
import com.example.food_basket_optimization.extraction.properties.base.multi.Multiplying;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UrlMultiPath implements UrlMultiProperties {

    private final String protocol;
    private final String host;
    private final List<MultiString> pathElements;

    public UrlMultiPath(String protocol,
                        String host,
                        List<MultiString> pathElements){
        this.pathElements = pathElements;
        this.protocol = protocol;
        this.host = host;

    }

    @Override
    public List<UrlProperty> multiply() {
        List<List<SimpleString>> multipliedPathElements = pathElements.stream().map(Multiplying::multiply).toList();
        List<List<SimpleString>> pathsElements = MultiplierUtil.directProduct(multipliedPathElements);


        return convertToUrlsProperties(pathsElements, protocol, host);
    }



    private List<UrlProperty> convertToUrlsProperties(List<List<SimpleString>> pathsElements, String protocol, String host){
       return pathsElements.stream().map(pathElements -> convertToUrlProperties(pathElements, protocol, host)).toList();
    }

    private UrlProperty convertToUrlProperties(List<SimpleString> pathElements, String protocol, String host){
        SimpleString pathProperty = createPathFromPathElements(pathElements);
        return new UrlBasicProperty(protocol,
                pathProperty.property(),
                host,
                pathProperty.getReferenceEntities());
    }



    private StringProperty createPathFromPathElements(List<SimpleString> pathElements){

        StringBuilder pathBuilder = new StringBuilder();
        List<ExtractedEntity> refEntities = new ArrayList<>();

        pathElements.forEach(pathElement -> {
            refEntities.addAll(pathElement.getReferenceEntities());
            pathBuilder.append('/').append(pathElement.property());
        });

        return new StringProperty(pathBuilder.toString(), refEntities);
    }




    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return pathElements.stream().map(MultiString::getRefClasses).flatMap(Collection::stream).toList();
    }
}
