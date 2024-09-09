package com.example.food_basket_optimization.extraction.properties.base.multi.properties;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.StringProperty;
import com.example.food_basket_optimization.extraction.properties.util.ExtractUtil;
import com.example.food_basket_optimization.extraction.properties.base.postmulti.PostMultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.util.RefValue;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiVisitor;
import com.example.food_basket_optimization.extraction.properties.visitor.ReferenceVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.function.IntFunction;

public class IterablePostContextualStringProperty implements PostMultiplyingProperty<SimpleProperty<String>> {
    private final Integer initValue;
    private final IntFunction<Integer> iterateFunction;
    private final ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractedContext;
    private final Class<? extends ReferencedExtractedEntity> clazz;
    private final RefValue refValue;




    public IterablePostContextualStringProperty(ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractedContext,
                                                Class<? extends ReferencedExtractedEntity> clazz,
                                                RefValue refValue,
                                                Integer initValue,
                                                IntFunction<Integer> iterateFunction
    ) {
        this.extractedContext = extractedContext;
        this.clazz = clazz;
        this.refValue = refValue;
        this.initValue = initValue;
        this.iterateFunction = iterateFunction;
    }


    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return List.of(clazz);
    }

    //todo: Make new logic.
    @Override
    public List<SimpleProperty<String>> multiply(List<? extends ExtractedEntity> refEntities) {
        List<? extends ExtractedEntity> extractedEntities = extractedContext.get(refValue.getRefClass());

        Optional<? extends ExtractedEntity> entityWithEqualReferences = extractedEntities.stream().filter(entity -> {
            if (entity instanceof ReferencedExtractedEntity refEntity) {
                return refEntity.referencesIsEqual(new ArrayList<>(refEntities));
            } else {
                return false;
            }
        }).findFirst();

        if(entityWithEqualReferences.isPresent()){
            Object fieldValueFromObject = ExtractUtil.getFieldValueFromObject(refValue.getFieldName(), entityWithEqualReferences.get());
            return iterate((Integer) fieldValueFromObject);
        }else{
            return iterate(10);
        }


    }

    private List<SimpleProperty<String>> iterate(int endValue) {
        List<SimpleProperty<String>> elements = new ArrayList<>();
        int startValue = initValue;

        elements.add(new StringProperty(String.valueOf(startValue)));
        while (true) {
            Integer iteratedValue = iterateFunction.apply(startValue);

            if (iteratedValue.compareTo(endValue) < 0) {
                elements.add(new StringProperty(String.valueOf(iteratedValue)));
            } else if (iteratedValue.compareTo(endValue) == 0) {
                elements.add(new StringProperty(String.valueOf(iteratedValue)));
                break;
            } else if (iteratedValue.compareTo(endValue) > 0) {
                elements.add(new StringProperty(String.valueOf(endValue)));
                break;
            }
            startValue = iteratedValue;
        }
        return elements;
    }

    @Override
    public void visit(MultiVisitor visitor) {
        visitor.post(this);
    }

    @Override
    public void visit(ReferenceVisitor visitor) {
        visitor.visit((ReferencedExtraction) this);
    }
}
