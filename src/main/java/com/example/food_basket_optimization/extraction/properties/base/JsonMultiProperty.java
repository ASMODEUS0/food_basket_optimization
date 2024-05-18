package com.example.food_basket_optimization.extraction.properties.base;

import com.example.food_basket_optimization.extraction.properties.base.multi.IterableStringProperty;
import com.example.food_basket_optimization.extraction.properties.base.multi.MultiString;
import com.example.food_basket_optimization.extraction.properties.base.multi.MultiStringProperty;
import com.example.food_basket_optimization.extraction.properties.base.prepostmulti.ComplexMultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.visitor.Multi;
import com.example.food_basket_optimization.extraction.properties.visitor.MultiVisitor;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class JsonMultiProperty implements ComplexMultiplyingProperty<JsonPostMultiProperty> {

    private final LinkedHashMap<String, MultiString> elements;

    @Override
    public List<JsonPostMultiProperty> multiply() {
//        Multi multi = null;
//        IntermediateMultiVisitor visitor = new IntermediateMultiVisitor();
//        List<String> keys = new ArrayList<>();
//        elements.forEach((key, value)->{
//            keys.add(key);
//            value.visit(visitor);
//        });
//        return null;

//        MultiPropertyContainer<MultiplyingProperty<?>> container = visitor.getPropertyContainer();
//        List<List<MultiplyingProperty<?>>> listOfObjectParams = container.getListOfObjectParams();
//
//        return listOfObjectParams.stream().map(params -> {
//            LinkedHashMap<String, MultiplyingProperty<SimpleString>> elements = params.stream().map(param -> {
//                int i = params.indexOf(param);
//                String currentKey = keys.get(i);
//                return new AbstractMap.SimpleEntry<>(currentKey, (MultiplyingProperty<SimpleString>) param);
//            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
//            return new JsonPostMultiProperty(elements);
//        }).toList();
        return null;
    }

    @Override
    public void visit(MultiVisitor visitor) {
//        elements.forEach(element ->{
//
//        });
//         visitor.complex(this);
    }
}
