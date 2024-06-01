package com.example.food_basket_optimization.extraction.properties;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiVisitor;
import com.example.food_basket_optimization.extraction.properties.visitor.ReferenceVisitor;

import java.util.ArrayList;
import java.util.List;

public abstract class SimplePropertyAbs<T> implements SimpleProperty<T>{
    private final List<ExtractedEntity> refEntities;

    public SimplePropertyAbs(List<ExtractedEntity> refEntities){
        this.refEntities = refEntities;
    }

    public SimplePropertyAbs(){
        this.refEntities = new ArrayList<>();
    }

    @Override
    public List<ExtractedEntity> getReferenceEntities() {
        return refEntities;
    }

    public void addRefEntities(List<ExtractedEntity> refEntities){
        this.refEntities.addAll(refEntities);
    }

    @Override
    public void visit(MultiVisitor visitor) {
        visitor.property(this);
    }

    @Override
    public void visit(ReferenceVisitor visitor) {
        visitor.visit(this);
    }
}
