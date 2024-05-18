package com.example.food_basket_optimization.extraction.properties.visitor;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.util.MultiplierUtil;
import com.example.food_basket_optimization.extraction.properties.util.PostMultiplyingProperty;

import java.util.*;

public class MultiPropertyContainer implements  PropertyContainerConstructor, PropertyContainer {

    private int serialNumber = 0;
    private final Map<Integer, List<Property<?>>> resolvedProperties;
    private final Map<Integer, PostMultiplyingProperty<?>> postResolvedProperties;

    public MultiPropertyContainer(){
        resolvedProperties = new HashMap<>();
        postResolvedProperties = new HashMap<>();
    }

    public void addResolvedProperty(List<Property<?>> property){
        resolvedProperties.put(serialNumber, property);
        serialNumber ++;
    }

    public void addPostProperties(PostMultiplyingProperty<?> postResolvedProperty){
        postResolvedProperties.put(serialNumber, postResolvedProperty);
        serialNumber++;
    }



    @Override
    public List<ExtractedEntity> getRefEntities() {
        ArrayList<ExtractedEntity> extractedEntities = new ArrayList<>();
        resolvedProperties.forEach(( serialNumber , resolvedProperty)->{
            extractedEntities.addAll(resolvedProperty.stream()
                    .map(Property::getReferenceEntities)
                    .flatMap(Collection::stream)
                    .toList());
        });
        return extractedEntities;
    }

    @Override
    public List<List<Property<?>>> getResolvedProperties(List<ExtractedEntity> refEntities) {
        List<List<Property<?>>> resolvedProperties = new ArrayList<>(Collections.nCopies(serialNumber, new ArrayList<Property<?>>()));
        this.resolvedProperties.forEach(resolvedProperties::set);
        postResolvedProperties.forEach((serialNumber, property)->{
            resolvedProperties.set(serialNumber, new ArrayList<>(property.multiply(refEntities)));
        });
       return MultiplierUtil.directProduct(resolvedProperties);
    }
}
