package com.example.food_basket_optimization.extractpojo.extractedentity.fiveka;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.annotation.JsonCollection;

@JsonCollection
public class FiveKaCategoryExt implements ExtractedEntity {

    public String id;
    public String name;
    public Object advert;
    public int products_count;
    public String image_link;
    public String gradient_start;
    public String gradient_end;
    public String title_color;
    public String description;
    public String description_md;
    public String description_html;

}
