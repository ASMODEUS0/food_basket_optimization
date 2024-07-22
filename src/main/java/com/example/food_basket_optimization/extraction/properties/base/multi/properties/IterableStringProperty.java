package com.example.food_basket_optimization.extraction.properties.base.multi.properties;

import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.StringProperty;
import com.example.food_basket_optimization.extraction.properties.base.multi.MultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiVisitor;
import com.example.food_basket_optimization.extraction.properties.visitor.ReferenceVisitor;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

public class IterableStringProperty implements MultiplyingProperty<SimpleProperty<String>> {
    private final String element;
    private final Integer initValue;
    private final Integer endValue;
    private final IntFunction<Integer> iterateFunction;

    public IterableStringProperty(String element, Integer initValue, Integer endValue, IntFunction<Integer> iterateFunction) {
        this.element = element;
        this.initValue = initValue;
        this.endValue = endValue;
        this.iterateFunction = iterateFunction;
    }


    public static Constructor<IterableStringProperty> getConstructor() {
        try {
            return IterableStringProperty.class.getConstructor(SimpleProperty.class, SimpleProperty.class, SimpleProperty.class, IntFunction.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    public List<SimpleProperty<String>> multiply() {
        List<SimpleProperty<String>> elements = new ArrayList<>();
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
    public void visit(MultiVisitor visitor) {
        visitor.multi(this);
    }

    @Override
    public void visit(ReferenceVisitor visitor) {

    }


}
