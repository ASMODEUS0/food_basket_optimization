package com.example.food_basket_optimization.extraction.properties.multi;

import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.util.MultiplierUtil;
import com.example.food_basket_optimization.extraction.properties.util.MultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.util.PostMultiplyingProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PropertyBuilder<T extends Property<?>> {
    private final List<MultiplyingProperty<?>> multiParameters;
    private final List<PostMultiplyingProperty<?>> postMultiParameters;
    private final Class<T> aClass;



    public PropertyBuilder(List<MultiplyingProperty<?>> multiParameters,
                           List<PostMultiplyingProperty<?>> postMultiParameters,
                           Class<T> aClass) {
        this.multiParameters = multiParameters;
        this.postMultiParameters = postMultiParameters;
        this.aClass = aClass;
    }


    public PropertyBuilder(List<MultiplyingProperty<?>> multiParameters,
                           Class<T> aClass) {
        this(multiParameters, new ArrayList<>(), aClass);
    }


    public List<T> build() {
        List<List<Property<?>>> multipliedParameters = new ArrayList<>();

        multiParameters.forEach(param -> multipliedParameters.add(new ArrayList<>(param.multiply())));

        List<List<Property<?>>> multitudeOfParams = MultiplierUtil.directProduct(multipliedParameters);


        if (postMultiParameters.isEmpty()) {

            return multitudeOfParams.stream().map(params -> MultiplierUtil.createPropertyFromMultipliedPropertiesParams(params, aClass)).toList();
        } else {

            List<List<Property<?>>> resultParams = postMultiParameters.stream()
                    .map(postMultiParam -> multitudeOfParams.stream()
                            .map(multipliedParameter -> resolvePostMultiplying(multipliedParameter, postMultiParam))
                            .flatMap(Collection::stream)
                            .toList())
                    .flatMap(Collection::stream)
                    .toList();
            return resultParams.stream().map(params -> MultiplierUtil.createPropertyFromMultipliedPropertiesParams(params, aClass)).toList();
        }

    }

    private List<List<Property<?>>> resolvePostMultiplying(List<Property<?>> propertyParams, PostMultiplyingProperty<?> postMultiProperty) {
//        List<? extends Property<?>> multipliedPostMultiParam = postMultiProperty.multiply(propertyParams);
//
//        return multipliedPostMultiParam.stream().map(postParam -> {
//            List<Property<?>> currentPropertyParams = new ArrayList<>(propertyParams);
//            currentPropertyParams.add(postParam);
//            return currentPropertyParams;
//        }).toList();
        return null;
    }
}
