package com.example.food_basket_optimization.importer.parser.service;

import com.example.food_basket_optimization.importer.parser.parsedobject.HttpObject;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestService {

   public static String doRequest(HttpObject httpObject){

       HttpClient httpClient =  HttpClient.newBuilder()
               .version(HttpClient.Version.HTTP_2)
               .build();

       switch (httpObject.getHttpMethod()){
           case GET -> {
               String result = doGet(httpObject, httpClient);
               return result;
           }
           case POST -> {
               String result = doPost(httpObject, httpClient);
               return result;
           }
           default -> {
               return null;
           }
       }

    }


    private static String doGet(HttpObject parseProperties, HttpClient client){
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


    private static String doPost(HttpObject parseProperties, HttpClient client){
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
