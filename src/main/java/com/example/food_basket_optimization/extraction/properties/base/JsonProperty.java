package com.example.food_basket_optimization.extraction.properties.base;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.base.keyvalue.basic.KeyValueBasicProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.SimpleString;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonProperty implements SimpleString {

   private final List<KeyValueBasicProperty> elements;
   private final List<ExtractedEntity> refEntities = new ArrayList<>();


   public static Constructor<? extends Property<?>> getConstructor(){
       try {
         return  JsonProperty.class.getConstructor(KeyValueBasicProperty[].class);
       } catch (NoSuchMethodException e) {
           throw new RuntimeException(e);
       }

   }

    public JsonProperty(KeyValueBasicProperty[] elements) {
        this.elements = Arrays.stream(elements).toList();
    }

    @Override
    public String property() {
        return convert(elements);
    }



    private String convert(List<KeyValueBasicProperty> elements) {
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append('{');

        elements.forEach(element ->{
            bodyBuilder.append('"').append(element.key()).append("\" ").append(':').append('"').append(element.value()).append('"');
            if(elements.get(elements.size() - 1) != element){
                bodyBuilder.append(',');
            }
        });

        bodyBuilder.append('}');

        return bodyBuilder.toString();
    }

    @Override
    public List<ExtractedEntity> getReferenceEntities() {
        return refEntities;
    }
}
