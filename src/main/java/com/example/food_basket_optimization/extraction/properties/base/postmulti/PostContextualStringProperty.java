package com.example.food_basket_optimization.extraction.properties.base.postmulti;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.StringProperty;
import com.example.food_basket_optimization.extraction.properties.util.ExtractUtil;
import com.example.food_basket_optimization.extraction.properties.util.RefValue;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiVisitor;
import com.example.food_basket_optimization.extraction.properties.visitor.ReferenceVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class PostContextualStringProperty implements PostMultiplyingProperty<SimpleProperty<String>> {


    private final ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractedContext;
//    private final Class<? extends ExtractedEntity> referencedClass;
    private final RefValue refValue;

    public PostContextualStringProperty(ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractedContext,
//                                        Class<? extends ExtractedEntity> referencedClass,
                                        RefValue refValue
    ) {
        this.extractedContext = extractedContext;
//        this.referencedClass = referencedClass;
        this.refValue = refValue;
    }

    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return List.of(refValue.getRefClass());
    }


    @Override
    public void visit(MultiVisitor visitor) {
        visitor.post(this);
    }

    @Override
    public void visit(ReferenceVisitor visitor) {
        visitor.visit((ReferencedExtraction) this);
    }

    @Override
    public List<SimpleProperty<String>> multiply(List<? extends ExtractedEntity> refEntities) {

        List<? extends ExtractedEntity> extractedEntities = extractedContext.get(refValue.getRefClass());
        List<? extends ExtractedEntity> entitiesWithEqualReferences = extractedEntities.stream().filter(entity -> {
            if (entity instanceof ReferencedExtractedEntity refEntity) {
                return refEntity.referencesIsEqual(new ArrayList<>(refEntities));
            } else {
                return false;
            }
        }).toList();

        if(entitiesWithEqualReferences.isEmpty()){
            throw new IllegalArgumentException("");
        }

        List<Object> referencedValues = entitiesWithEqualReferences.stream()
                .map(entity -> ExtractUtil.getFieldValueFromObject(refValue.getFieldName(), entity))
                .toList();


        return referencedValues.stream().map(object-> new StringProperty( object.toString())).collect(Collectors.toList());

    }
}
