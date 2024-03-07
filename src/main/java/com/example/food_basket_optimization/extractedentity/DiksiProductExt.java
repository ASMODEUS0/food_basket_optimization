package com.example.food_basket_optimization.extractedentity;


import com.example.food_basket_optimization.entity.Product;
import com.example.food_basket_optimization.extraction.ExtractedEntityMappedObject;
import com.example.food_basket_optimization.extraction.properties.mapping.htmlmap.annotation.Contextual;
import com.example.food_basket_optimization.extraction.properties.mapping.htmlmap.annotation.OppositeSide;
import com.example.food_basket_optimization.extraction.properties.mapping.htmlmap.annotation.Select;

@Select(value = "div.product-container")
public class DiksiProductExt implements ExtractedEntityMappedObject<Product> {
    @Select(value = "div.dixyCatalogItem__title")
    private String name;
    @Select(value = "p[content]")
    private String price;
    @Contextual(key = "Cookie")
    @OppositeSide(methodName = "addProduct")
    private DiksiCityExtr city;

    @Override
    public Product map(Object... entities) {
        return null;
    }
}
