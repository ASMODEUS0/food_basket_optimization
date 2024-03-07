package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extraction.properties.util.ExtractUtil;
import com.example.food_basket_optimization.extraction.properties.util.RefValue;

import java.net.URL;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.concurrent.ConcurrentMap;

public class UrlContextualPathProperties extends UrlPropertiesAbs implements UrlMultiProperties, ReferencedExtraction {

    private final ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext;
    private final List<String> pathElements;
    private final List<Class<? extends ExtractedEntity>> refClasses = new ArrayList<>();

    public UrlContextualPathProperties(String protocol,
                                       List<String> pathElements,
                                       String host,
                                       ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext) {
        super(protocol, createPathFromPathElements(pathElements), host);
        this.extractContext = extractContext;
        this.pathElements = pathElements;
        refClasses.addAll(ExtractUtil.detectStringReference(pathElements));
    }

    @Override
    public List<? extends UrlBasicProperties> multiply() {

        List<String> pathElementsToMultiply = pathElements.stream()
                .filter(pathElement -> ExtractUtil.resolveRefValue(pathElement).isPresent())
                .toList();



        List<SimpleEntry<List<? extends ExtractedEntity>, List<String>>> refObjectsWithMultipliedPaths = multiplyPaths(List.of(new SimpleEntry<>(new ArrayList<>(), pathElements)), new ArrayList<>(pathElementsToMultiply));

        return refObjectsWithMultipliedPaths.stream().map(refObjectWithMultipliedPaths -> {
            List<String> pathElements = refObjectWithMultipliedPaths.getValue();
            List<? extends ExtractedEntity> refEntities = refObjectWithMultipliedPaths.getKey();
            String multipliedPath = createPathFromPathElements(pathElements);
            return new UrlBasicContextualProperties(getProtocol(), multipliedPath, getHost(), refEntities);
        }).toList();
    }

    private List<SimpleEntry<List<? extends ExtractedEntity>, List<String>>> multiplyPaths(List<SimpleEntry<List<? extends ExtractedEntity>, List<String>>> refEntitiesWithPaths,
                                                                                           List<String> pathElementsToMultiply) {

        String pathElementToMultiply = pathElementsToMultiply.get(0);
        Optional<RefValue> mayBeRefValue = ExtractUtil.resolveRefValue(pathElementToMultiply);

        if (pathElementToMultiply == null || mayBeRefValue.isEmpty()) {
            return refEntitiesWithPaths;
        }

        RefValue refValue = mayBeRefValue.get();
        Map<? extends ExtractedEntity, Object> objectFieldValueMap = ExtractUtil.getValueFromField(refValue.getRefClass(), refValue.getFieldName(), extractContext);

        List<SimpleEntry<List<? extends ExtractedEntity>, List<String>>> result = new ArrayList<>();

        refEntitiesWithPaths.forEach(refEntitiesWithPath -> {
            List<String> path = refEntitiesWithPath.getValue();
            List<? extends ExtractedEntity> refEntities = refEntitiesWithPath.getKey();
            int indexOfCurMultiplyElement = path.indexOf(pathElementToMultiply);
            if (indexOfCurMultiplyElement != -1) {

                objectFieldValueMap.forEach((object, fieldValue) -> {

                    List<String> curPath = new ArrayList<>(path);
                    List<ExtractedEntity> curRefEntities = new ArrayList<>(refEntities);
                    curRefEntities.add(object);
                    curPath.set(indexOfCurMultiplyElement, (String) fieldValue);
                    result.add(new SimpleEntry<>(curRefEntities, curPath));

                });

            }
        });

        pathElementsToMultiply.remove(pathElementToMultiply);
        return pathElementsToMultiply.isEmpty() ? result : multiplyPaths(result, pathElementsToMultiply);
    }


//    @Override
//    public List<? extends UrlBasicProperties> multiply() {
//        return null;

//        List<List<String>> paths = new ArrayList<>(List.of(pathElements));
//
//
//        List<String> pathElementsToMultiply = pathElements.stream()
//                .filter(pathElement -> ExtractUtil.resolveRefValue(pathElement).isPresent())
//                .toList();
//
//
//        if (pathElementsToMultiply.isEmpty()) {
//            return paths.stream().map(pathElements -> {
//                return new UrlBasicProperties(getProtocol(), createPathFromPathElements(pathElements), getHost());
//            }).toList();
//        }
//
//
//        for (String pathElementToMultiply : pathElementsToMultiply) {
//
//            int i = pathElements.indexOf(pathElementToMultiply);
//
//            Optional<RefValue> refValue = ExtractUtil.resolveRefValue(pathElementToMultiply);
//
//            if (i == -1 || refValue.isEmpty()) {
//                continue;
//            }
//
//            ArrayList<List<String>> copyPaths = new ArrayList<>(paths);
//
//            RefValue currentRefValue = refValue.get();
//            Map<ExtractedEntity, Object> valueOfFieldMap = ExtractUtil.getValueFromField(currentRefValue.getRefClass(), currentRefValue.fieldName, extractContext);
//
//
//            paths = copyPaths.stream().flatMap(path -> {
//                ArrayList<List<String>> pathsMultiplied = new ArrayList<>();
//
//                valueOfFieldMap.forEach((object, value) -> {
//                    ArrayList<String> pathMultiplied = new ArrayList<>(path);
//
//                    int i1 = pathMultiplied.indexOf(pathElementToMultiply);
//                    if (i1 != -1) {
//                        pathMultiplied.set(i1, (String) value);
//                    }
//                    pathsMultiplied.add(pathMultiplied);
//                });
//
//                return pathsMultiplied.stream();
//            }).toList();
//
//        }
//
//
//        return paths.stream().map(pathElements -> {
//            return new UrlBasicProperties(getProtocol(), createPathFromPathElements(pathElements), getHost());
//        }).toList();
//    }




    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return refClasses;
    }
}
