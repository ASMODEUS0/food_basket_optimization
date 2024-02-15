package com.example.food_basket_optimization.pojo;


import com.example.food_basket_optimization.importer.parser.mapping.Contextual;
import com.example.food_basket_optimization.importer.parser.mapping.Select;
import org.hibernate.id.IntegralDataTypeHolder;

@Select(value = "div.product-container")
public class DiksiProduct {
    @Select(value = "div.dixyCatalogItem__title")
    private String name;
    @Select(value = "p[content]")
    private String price;
    @Contextual(key = "Cookie")
    private DiksiCity city;
}
