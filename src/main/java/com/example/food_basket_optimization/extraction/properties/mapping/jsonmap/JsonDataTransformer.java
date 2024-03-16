package com.example.food_basket_optimization.extraction.properties.mapping.jsonmap;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataTransformer {

    public static String transformDataToClass(String data, Class<?> clazz) {
        ObjectMapper om = new ObjectMapper();
        return transformBaseOnAnnotation(data, clazz, om);
    }

    private static String transformBaseOnAnnotation(String data ,Class<?> clazz, ObjectMapper om){
        if(clazz.isAnnotationPresent(JsonRootName.class)){
            String value = clazz.getAnnotation(JsonRootName.class).value();
            try {
                JsonNode jsonNode = om.readTree(data);
                JsonNode transformedNode = jsonNode.get(value);
                if(transformedNode != null){
                    return transformedNode.toString();
                }
            } catch (JsonProcessingException e) {
                return data;
            }
        }
       return data;
    }


}
