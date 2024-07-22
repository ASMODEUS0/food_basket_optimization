package com.example.food_basket_optimization.extractpojo.extractedentity.magnit;

import com.example.food_basket_optimization.entity.Product;
import com.example.food_basket_optimization.entity.ShopType;
import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.ExtractedEntityMappedObject;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.annotation.JsonCollection;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.annotation.JsonRootElement;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

@JsonRootName("goods")
@JsonCollection
public class MagnitProductExt implements ExtractedEntityMappedObject<Product> {
    public ArrayList<Object> categories;
    public String code;
    public String id;
    public Image image;
    public boolean isForAdults;
    public String name;
    public ArrayList<Offer> offers;
    public int grammar;
    public String unitValue;


    @Override
    public Product map(Object... args) {
        String price = offers.get(0).price.replace(',', '.');
        return Product.builder()
                .price(Double.valueOf(price))
                .title(name)
                .brand(name)
                .subTitle(name)
                .imageUrl(image.prefixUrl + image.postfixUrl)
                .stock(offers.get(0).quantity)
                .shopType(ShopType.MAGNIT)
                .build();
    }
}
