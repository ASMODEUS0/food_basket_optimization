package com.example.food_basket_optimization;

import com.example.food_basket_optimization.pojo.ShopPOJO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class JacksonTest {


    @Test
    public void test1() throws IOException {

        ObjectMapper om = new ObjectMapper();

        om.

        File file = new File("/Users/nikitauporov/Desktop/Диплом/food_basket_optimization/src/main/resources/text.json");
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ShopPOJO shop = om.readValue(file, ShopPOJO.class);
        System.out.println(shop.getRetailerName());

    }
    @Test
    public void test2() throws IOException {
        File file = new File("/Users/nikitauporov/Desktop/Диплом/food_basket_optimization/src/main/resources/text.json");

        JsonNode jsonNode = new ObjectMapper().readTree(file);
        Iterator<JsonNode> elements = jsonNode.elements();

    }




    @Test
    public void test4() throws IOException {
        File file = new File("/Users/nikitauporov/Desktop/Диплом/food_basket_optimization/src/main/resources/text.json");

        ObjectMapper objectMapper = new ObjectMapper();
        try (JsonParser parser = objectMapper.createParser(file)) {
            JsonNode treeNode = parser.readValueAsTree();


        }

//        annotation.


    }
}
