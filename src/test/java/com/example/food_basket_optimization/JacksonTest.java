package com.example.food_basket_optimization;

import com.example.food_basket_optimization.pojo.ShopPOJO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;

public class JacksonTest {


    @Test
    public void test1() throws IOException {

   
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


    @Test
    public void testRequest() throws IOException, InterruptedException {
        HttpClient client  = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();


        HttpRequest request = HttpRequest.newBuilder(URI.create("https://sbermarket.ru/metro"))
                .GET()
                .build();


        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());

        String body = send.body();
        System.out.println();
    }
}
