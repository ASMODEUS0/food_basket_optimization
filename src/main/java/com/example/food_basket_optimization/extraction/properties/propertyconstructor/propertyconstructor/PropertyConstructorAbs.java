package com.example.food_basket_optimization.extraction.properties.propertyconstructor.propertyconstructor;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.base.postmulti.PostMultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.PropertyParamsContainer;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.PropertySource;

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

        //Defining parameters
        Map<Integer, PostMultiplyingProperty<?>> indexPostParamMap = source.postResolvedProperties();
        Map<Integer, PropertyConstructor<?>> indexConstructedParamMap = source.constructedResolvedProperties();
        PropertyParamsContainer initContainer = new PropertyParamsContainer(source.resolvedProperties());

        List<PropertyParamsContainer> paramsContainers = new ArrayList<>(List.of(initContainer));

        for(Map.Entry<Integer, PropertyConstructor<?>> indexConstructedParam : indexConstructedParamMap.entrySet()){
            PropertyConstructor<?> constructedParam = indexConstructedParam.getValue();
            Integer index = indexConstructedParam.getKey();


            List<PropertyParamsContainer> resultContainers = new ArrayList<>();


            for(PropertyParamsContainer paramsContainer: paramsContainers){
                ArrayList<ExtractedEntity> objects = new ArrayList<>(refEntities);
                objects.addAll(paramsContainer.getRefEntities());
                List<? extends SimpleProperty<?>> params = constructedParam.postConstruct(objects);

              resultContainers = params.stream().map(param -> paramsContainer.doCopyWithSetParam(index, param)).toList();

            }

            paramsContainers = resultContainers;
        }

        for(Map.Entry<Integer, PostMultiplyingProperty<?>> indexPostParam : indexPostParamMap.entrySet()){

            PostMultiplyingProperty<?> constructedParam = indexPostParam.getValue();
            Integer index = indexPostParam.getKey();


            List<PropertyParamsContainer> resultContainers = new ArrayList<>();

            for(PropertyParamsContainer paramsContainer: paramsContainers){

                ArrayList<ExtractedEntity> objects = new ArrayList<>(refEntities);
                objects.addAll(paramsContainer.getRefEntities());
                List<? extends SimpleProperty<?>> params = constructedParam.multiply(objects);

                resultContainers = params.stream().map(param -> paramsContainer.doCopyWithSetParam(index, param)).toList();

            }

            paramsContainers = resultContainers;
        }

        return paramsContainers.stream().map(PropertyParamsContainer::toList).toList();
    }


    @Override
    public List<ExtractedEntity> getRefEntities() {
        return source.getRefEntities();
    }
}
