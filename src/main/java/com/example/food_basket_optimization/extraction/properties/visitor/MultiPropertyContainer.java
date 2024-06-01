package com.example.food_basket_optimization.extraction.properties.visitor;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.StringProperty;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.PropertySource;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.propertyconstructor.PropertyConstructor;
import com.example.food_basket_optimization.extraction.properties.util.MultiplierUtil;
import com.example.food_basket_optimization.extraction.properties.base.postmulti.PostMultiplyingProperty;

import java.util.*;
import java.util.stream.Collectors;

public class MultiPropertyContainer implements PropertyContainerConstructor, PropertyContainer {


    private int serialNumber = 0;
    private final Map<Integer, List<SimpleProperty<?>>> resolvedProperties;
    private final Map<Integer, List<PropertyConstructor<?>>> constructedParams;
    private final Map<Integer, PostMultiplyingProperty<?>> postResolvedProperties;

    public MultiPropertyContainer() {
        resolvedProperties = new HashMap<>();
        postResolvedProperties = new HashMap<>();
        constructedParams = new HashMap<>();
    }

    public void addResolvedProperty(List<SimpleProperty<?>> property) {
        resolvedProperties.put(serialNumber, property);
        serialNumber++;
    }

    public void addPostProperties(PostMultiplyingProperty<?> postResolvedProperty) {
        postResolvedProperties.put(serialNumber, postResolvedProperty);
        serialNumber++;
    }

    public void addConstructableProperty(List<PropertyConstructor<?>> constructableProperty) {
        constructedParams.put(serialNumber, constructableProperty);
        serialNumber++;
    }

    @Override
    public List<ExtractedEntity> getRefEntities() {
        ArrayList<ExtractedEntity> refEntities = new ArrayList<>();
        resolvedProperties.forEach((serialNumber, resolvedProperty) -> refEntities.addAll(resolvedProperty.stream()
                .map(SimpleProperty::getReferenceEntities)
                .flatMap(Collection::stream)
                .toList()));

        return refEntities;
    }


    @Override
    public List<PropertySource> getSources() {

        List<List<SimpleProperty<?>>> preResolvedParams = new ArrayList<>(Collections.nCopies(serialNumber, new ArrayList<>()));


        //Собирает в один массив, все массивы элементов.
        resolvedProperties.forEach((serialNumber, resolvedProperty) -> preResolvedParams.set(serialNumber, new ArrayList<>(resolvedProperty)));

        constructedParams.forEach((serialNumber, property) -> preResolvedParams.set(serialNumber, List.of(new StringProperty(""))));

        postResolvedProperties.forEach((serialNumber, property) -> {
            StringProperty e1 = new StringProperty("");
            preResolvedParams.set(serialNumber, List.of(e1));
        });

        // Перемножает элементы массива, получается массив содержащий массивы параметров объекта
        List<List<SimpleProperty<?>>> listOfObjectParams = MultiplierUtil.directProduct(preResolvedParams);


        List<List<PropertyConstructor<?>>> constructedParams = new ArrayList<>();
        List<Integer> serialNumbers = new ArrayList<>();

        this.constructedParams.forEach((serialNumber, properties) -> {
            constructedParams.add(properties);
            serialNumbers.add(serialNumber);
        });

        List<List<PropertyConstructor<?>>> distributedConstructedListedParams = MultiplierUtil.directProduct(constructedParams);


        return listOfObjectParams.stream().flatMap(objectParams -> distributedConstructedListedParams.stream().map(distributedConstructedParams -> {
            Map<Integer, PropertyConstructor<?>> constructedMap = distributedConstructedParams.stream().map(param -> {
                int i = distributedConstructedParams.indexOf(param);
                return new AbstractMap.SimpleEntry<>(serialNumbers.get(i), param);
            }).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
            return new PropertySource(objectParams, postResolvedProperties, constructedMap);
        })).collect(Collectors.toList());
    }

}
