package com.example.food_basket_optimization.extractpojo.extractedentity.magnit.body;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.SimplePropertyAbs;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pagination extends SimplePropertyAbs<String> {


    public static Constructor<Pagination> getConstructor() {
        try {
            return Pagination.class.getConstructor(SimpleProperty.class, SimpleProperty.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }



    public String number;
    public String size;


    public Pagination(SimpleProperty<String> number,
                      SimpleProperty<String> size){
//        List<ExtractedEntity> refEntities = new ArrayList<>(number.getReferenceEntities());
//        refEntities.addAll(size.getReferenceEntities());
////        super(Collections.unmodifiableList(refEntities));
//        this.number = number.property();
//        this.size = size.property();
    }


    @Override
    public String property() {
        return null;
    }
}
