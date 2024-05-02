package com.example.food_basket_optimization.extractpojo.extractedentity.diksi;


import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.htmlmap.annotation.Attribute;
import com.example.food_basket_optimization.extraction.properties.mapping.htmlmap.annotation.Select;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Select(value = "a.region-link")
@NoArgsConstructor
public class DiksiCityExtr implements ExtractedEntity {
    @Attribute(value = "data-region-id")
    private String id;

    @Select(value = "a.region-link")
    private String name;


    private List<DiksiProductExt> products = new ArrayList<>();


    public void addProduct(DiksiProductExt product){
        products.add(product);
    }


}
