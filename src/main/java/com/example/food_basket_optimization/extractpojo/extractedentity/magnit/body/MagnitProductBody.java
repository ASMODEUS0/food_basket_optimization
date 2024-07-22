package com.example.food_basket_optimization.extractpojo.extractedentity.magnit.body;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.SimplePropertyAbs;
import com.example.food_basket_optimization.extraction.properties.base.simple.JsonProperty;

import java.lang.reflect.Constructor;
import java.util.List;

public class MagnitProductBody extends SimplePropertyAbs<String> {


    public static Constructor<MagnitProductBody> getConstructor() {
        try {
            return MagnitProductBody.class.getConstructor(SimpleProperty.class, SimpleProperty.class, SimpleProperty.class, SimpleProperty.class, Pagination.class, SimpleProperty.class, SimpleProperty.class, SimpleProperty.class, List.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }



    public String categoryIDs;
    public String includeForAdults;
    public String onlyDiscount;
    public String order;
    public Pagination pagination;
    public String shopType;
    public String sortBy;
    public String storeCodes;
    public List<Filter> filters;



    public MagnitProductBody(SimpleProperty<String> categoryIDs,
                             SimpleProperty<String> includeForAdults,
                             SimpleProperty<String> onlyDiscount,
                             SimpleProperty<String> order,
                             Pagination pagination,
                             SimpleProperty<String> shopType,
                             SimpleProperty<String> sortBy,
                             SimpleProperty<String> storeCodes,
                             List<Filter> filters) {

        this.categoryIDs = categoryIDs.property();
        this.includeForAdults = includeForAdults.property();
        this.onlyDiscount = onlyDiscount.property();
        this.order = order.property();
        this.pagination = pagination;
        this.shopType = shopType.property();
        this.sortBy = sortBy.property();
        this.storeCodes = storeCodes.property();
        this.filters = filters;
    }


    @Override
    public List<ExtractedEntity> getReferenceEntities() {
        return List.of();
    }

    @Override
    public String property() {
        throw new IllegalArgumentException();
    }
}

