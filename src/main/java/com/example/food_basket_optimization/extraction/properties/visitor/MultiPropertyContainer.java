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

    private int paramIndex = 0;
    private final Map<Integer, List<SimpleProperty<?>>> resolvedParams;
    private final Map<Integer, List<PropertyConstructor<?>>> constructedParams;
    private final Map<Integer, PostMultiplyingProperty<?>> postResolvedParams;

    public MultiPropertyContainer() {
        resolvedParams = new HashMap<>();
        postResolvedParams = new HashMap<>();
        constructedParams = new HashMap<>();
    }

    public void addResolvedParam(List<SimpleProperty<?>> resolvedParam) {
        resolvedParams.put(paramIndex, resolvedParam);
        paramIndex++;
    }

    public void addPostParam(PostMultiplyingProperty<?> postResolvingParam) {
        postResolvedParams.put(paramIndex, postResolvingParam);
        paramIndex++;
    }

    public void addConstructableProperty(List<PropertyConstructor<?>> constructableProperty) {
        constructedParams.put(paramIndex, constructableProperty);
        paramIndex++;
    }

    @Override
    public List<ExtractedEntity> getRefEntities() {
        ArrayList<ExtractedEntity> refEntities = new ArrayList<>();
        resolvedParams.forEach((serialNumber, resolvedProperty) -> refEntities.addAll(resolvedProperty.stream()
                .map(SimpleProperty::getReferenceEntities)
                .flatMap(Collection::stream)
                .toList()));

        return refEntities;
    }


    @Override
    public List<PropertySource> getSources() {

        List<List<SimpleProperty<?>>> preResolvedParams = new ArrayList<>(Collections.nCopies(paramIndex, new ArrayList<>()));


        //Собирает в один массив, все массивы элементов.
        resolvedParams.forEach((serialNumber, resolvedProperty) -> preResolvedParams.set(serialNumber, new ArrayList<>(resolvedProperty)));

        constructedParams.forEach((serialNumber, property) -> preResolvedParams.set(serialNumber, List.of(new StringProperty(""))));

        postResolvedParams.forEach((serialNumber, property) -> preResolvedParams.set(serialNumber, List.of(new StringProperty(""))));

        // Перемножает элементы массива - получается массив содержащий массивы параметров объекта
        List<List<SimpleProperty<?>>> listOfObjectParams = MultiplierUtil.directProduct(preResolvedParams);


        List<List<PropertyConstructor<?>>> constructedParams = new ArrayList<>();
        List<Integer> serialNumbers = new ArrayList<>();

        this.constructedParams.forEach((serialNumber, properties) -> {
            constructedParams.add(properties);
            serialNumbers.add(serialNumber);
        });

        List<List<PropertyConstructor<?>>> distributedConstructedListedParams = MultiplierUtil.directProduct(constructedParams);


        return listOfObjectParams.stream().flatMap(objectParams ->
                distributedConstructedListedParams.stream().map(distributedConstructedParams -> {

            Map<Integer, PropertyConstructor<?>> constructedMap = distributedConstructedParams.stream().map(param -> {
                int i = distributedConstructedParams.indexOf(param);
                return new AbstractMap.SimpleEntry<>(serialNumbers.get(i), param);
            }).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));

            return new PropertySource(objectParams, postResolvedParams, constructedMap);
        })).collect(Collectors.toList());
    }

}
