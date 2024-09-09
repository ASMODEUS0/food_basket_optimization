package com.example.food_basket_optimization.extractpojo.extractedentity.lenta;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.annotation.JsonCollection;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;

@JsonRootName("categories")
@JsonCollection
public class LentaCategoryExt implements ExtractedEntity {
    public ArrayList<Object> badges;
    public boolean hasChildren;
    public String iconUrl;
    public int id;
    public String imageUrl;
    public String imageWebUrl;
    public boolean isAdult;
    public int level;
    public String name;
    public int parentId;
    public String parentName;
    public String slug;
}
