package com.example.food_basket_optimization.refresh.parser.service;

import com.example.food_basket_optimization.refresh.parser.properties.htpp.HTTPParseProperties;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestService {

   public static String doRequest(HTTPParseProperties parseProperties){

       HttpClient httpClient =  HttpClient.newBuilder()
               .version(HttpClient.Version.HTTP_2)
               .build();

       switch (parseProperties.getHttpMethod()){
           case GET -> {
               String result = doGet(parseProperties, httpClient);
               return result;

           }
           case POST -> {
               String result = doPost(parseProperties, httpClient);
               return result;
           }
           default -> {
               return null;
           }
       }

    }


    private static String doGet(HTTPParseProperties parseProperties, HttpClient client){
        HttpRequest request = HttpRequest.newBuilder(parseProperties.getUri())
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private static String doPost(HTTPParseProperties parseProperties, HttpClient client){
        HttpRequest request = HttpRequest.newBuilder(parseProperties.getUri())
                .POST(HttpRequest.BodyPublishers.ofString(parseProperties.getBody()))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
