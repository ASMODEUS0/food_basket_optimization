package com.example.food_basket_optimization.extraction.properties.base.multi;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.base.simple.SimpleString;
import com.example.food_basket_optimization.extraction.properties.base.simple.StringProperty;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiVisitor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

@RequiredArgsConstructor
public class IterableStringProperty implements MultiString{
    private final String element;
    private final Integer initValue;
    private final Integer endValue;
    private final IntFunction<Integer> iterateFunction;

    @Override
    public List<SimpleString> multiply() {
        List<SimpleString> elements = new ArrayList<>();
        int startValue = initValue;

        elements.add(new StringProperty(element + startValue));
        while(true){
            Integer iteratedValue = iterateFunction.apply(startValue);

            if(iteratedValue.compareTo(endValue) < 0 ){
                elements.add(new StringProperty(element + iteratedValue));
            }else if(iteratedValue.compareTo(endValue) == 0 ){
                elements.add(new StringProperty(element + iteratedValue));
                break;
            } else if (iteratedValue.compareTo(endValue) > 0) {
                elements.add(new StringProperty(element + endValue));
                break;
            }
            startValue = iteratedValue;
        }
        return elements;
    }

    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return List.of();
    }

    @Override
    public void visit(MultiVisitor visitor) {
        visitor.multi(this);
    }
}
