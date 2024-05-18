package com.example.food_basket_optimization.extraction.properties.propertyconstructor;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.base.JsonProperty;
import com.example.food_basket_optimization.extraction.properties.base.keyvalue.multi.KeyContextualValue;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.SourceHttpProperty;
import com.example.food_basket_optimization.extraction.properties.util.MultiplierUtil;
import com.example.food_basket_optimization.extraction.properties.util.RefValue;
import com.example.food_basket_optimization.extractpojo.extractedentity.lenta.LentaCityExt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConstructableObject<T extends Property<?>> implements ConstructableRootObject {
    private final List<ConstructableParameterImpl> params;
    private final Class<T> clazz;
    private final List<ExtractedEntity> refEntities;


    public static void main(String[] args) {
        LentaCityExt lentaCity1 = new LentaCityExt();
        LentaCityExt lentaCity2 = new LentaCityExt();
        LentaCityExt lentaCity3 = new LentaCityExt();

        lentaCity1.name = "London";
        lentaCity2.name = "Prague";
        lentaCity3.name = "Munich";


        ArrayList<ConstructableParameterImpl> constructableParameterImpls = new ArrayList<>();

        ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> context = new ConcurrentHashMap<>();
        context.put(LentaCityExt.class, List.of(lentaCity1, lentaCity2, lentaCity3));

        ConstructableParameterImpl param1 = new ConstructableParameterImpl(JsonProperty.getConstructor(), List.of(new KeyContextualValue("fuck",
                context,
                new RefValue(LentaCityExt.class, "name"))));
        constructableParameterImpls.add(param1);
        ConstructableObject<SourceHttpProperty> constructableObject = new ConstructableObject<>(constructableParameterImpls, SourceHttpProperty.class);

        List<SourceHttpProperty> construct = constructableObject.construct();


    }

    public ConstructableObject(List<ConstructableParameterImpl> params,
                               Class<T> clazz){
        this.params = params;
        this.clazz = clazz;
        refEntities = new ArrayList<>();
        params.forEach(param->{
            param.registerRootObject(this);
        });

    }

    List<T> construct(){
        List<List<Property<?>>> resolvedParams = params.stream().map(ConstructableParameterImpl::constructParameter).toList();
        List<List<Property<?>>> lists = MultiplierUtil.directProduct(resolvedParams);


        return null;
    }

    @Override
    public void setRefExtractedEntities(List<ExtractedEntity> refEntities) {
        this.refEntities.addAll(refEntities);
    }

    @Override
    public List<ExtractedEntity> getRefEntities() {
        return refEntities;
    }
}
