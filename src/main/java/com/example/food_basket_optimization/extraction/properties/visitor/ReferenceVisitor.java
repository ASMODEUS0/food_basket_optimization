package com.example.food_basket_optimization.extraction.properties.visitor;

import com.example.food_basket_optimization.extraction.ReferencedExtraction;
import com.example.food_basket_optimization.extraction.properties.Property;

public interface ReferenceVisitor {
    void visitReferenced(ReferencedExtraction referencedProperty);
    void visit(Property property);
}
