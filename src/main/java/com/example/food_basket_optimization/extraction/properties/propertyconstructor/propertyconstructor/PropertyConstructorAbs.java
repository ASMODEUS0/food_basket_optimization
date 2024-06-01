package com.example.food_basket_optimization.extraction.properties.propertyconstructor.propertyconstructor;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.base.postmulti.PostMultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.PropertySource;
import com.example.food_basket_optimization.extraction.properties.util.MultiplierUtil;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class PropertyConstructorAbs<T extends SimpleProperty<?>> implements PropertyConstructor<T> {

    protected final PropertySource source;
    protected final Constructor<T> constructor;


    public PropertyConstructorAbs(PropertySource source, Constructor<T> constructor) {
        this.source = source;
        this.constructor = constructor;
    }

    protected List<List<SimpleProperty<?>>> resolvePostConstruct(List<ExtractedEntity> refEntities){

        Map<Integer, PostMultiplyingProperty<?>> indexPostParamMap = source.getPostResolvedProperties();
        Map<Integer, PropertyConstructor<?>> indexConstructedParamMap = source.getConstructedResolvedProperties();
        List<SimpleProperty<?>> resolvedParams = source.getResolvedProperties();

        List<List<SimpleProperty<?>>> result = new ArrayList<>(List.of(resolvedParams));

        List<ExtractedEntity> currentRefEntities = new ArrayList<>(source.getRefEntities());
        currentRefEntities.addAll(refEntities);

        for(Map.Entry<Integer, PropertyConstructor<?>> indexConstructedParam : indexConstructedParamMap.entrySet()){

            PropertyConstructor<?> constructedParam = indexConstructedParam.getValue();
            Integer index = indexConstructedParam.getKey();

            List<? extends SimpleProperty<?>> params = constructedParam.postConstruct(currentRefEntities);

            List<List<SimpleProperty<?>>> resultCopy = new ArrayList<>(result);

            result = resultCopy.stream().flatMap(resultParams-> MultiplierUtil.replaceValue(index, resultParams, new ArrayList<>(params)).stream()).toList();


        }

        for(Map.Entry<Integer, PostMultiplyingProperty<?>> indexPostParam : indexPostParamMap.entrySet()){

            PostMultiplyingProperty<?> constructedParam = indexPostParam.getValue();
            Integer index = indexPostParam.getKey();

            List<? extends SimpleProperty<?>> params = constructedParam.multiply(currentRefEntities);
            List<List<SimpleProperty<?>>> resultCopy = new ArrayList<>(result);
            result = resultCopy.stream().flatMap(resultParams-> MultiplierUtil.replaceValue(index, resultParams, new ArrayList<>(params)).stream()).toList();
        }

        return result;
    }


    @Override
    public List<ExtractedEntity> getRefEntities() {
        return source.getRefEntities();
    }
}
