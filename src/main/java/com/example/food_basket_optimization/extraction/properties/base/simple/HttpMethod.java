package com.example.food_basket_optimization.extraction.properties.base.simple;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.SimpleString;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiVisitor;
import com.example.food_basket_optimization.extraction.properties.visitor.ReferenceVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public enum HttpMethod implements SimpleProperty<String> {

    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private final String methodName;

    HttpMethod(String methodName){
      this.methodName = methodName;
    }

    @Override
    public List<ExtractedEntity> getReferenceEntities() {
        return List.of();
    }

    @Override
    public String property() {
        return methodName;
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
