package com.example.food_basket_optimization.extraction.properties.base.postmulti;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ReferencedExtractedEntity;
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
//todo: May be deleted
public class PostContextualStringProperty implements PostMultiplyingProperty<SimpleProperty<String>> {


    private final ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractedContext;
    private final Class<? extends ReferencedExtractedEntity> clazz;
    private final RefValue refValue;

    public PostContextualStringProperty(ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractedContext,
                                        Class<? extends ReferencedExtractedEntity> clazz,
                                        RefValue refValue
    ) {
        this.extractedContext = extractedContext;
        this.clazz = clazz;
        this.refValue = refValue;
    }

    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return List.of();
    }


    @Override
    public void visit(MultiVisitor visitor) {
        visitor.post(this);
    }

    @Override
    public void visit(ReferenceVisitor visitor) {
        visitor.visit(this);
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

        List<Object> list = entitiesWithEqualReferences.stream()
                .map(entity -> ExtractUtil.getFieldValueFromObject(refValue.getFieldName(), entity))
                .toList();

        return list.stream().map(object-> new StringProperty( object.toString())).collect(Collectors.toList());
    }
}
