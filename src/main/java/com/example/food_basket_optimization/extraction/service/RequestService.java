package com.example.food_basket_optimization.extraction.service;

import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.SourceHttp;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class RequestService {

    static int count;

    private static HttpClient client;


    static {
        client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }


    public static List<String> doRequests(List<HttpRequest> requests) {
        return requests.stream().map(RequestService::doRequest).toList();
    }



    public static String doRequest(HttpRequest request) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static String doRequest1(SourceHttp source) {

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();


        if (source.getParams() != null) {
//            System.out.println("Request: " + source.getParams().get(0).value() + " count: " + count);

            count += 1;
        }

        switch (source.getMethod()) {
            case GET -> {
                String result = doGet1(source, httpClient);
                return result;
            }
            case POST -> {
                String result = doPost1(source, httpClient);
                return result;
            }
            default -> {
                return null;
            }
        }

    }


    private static String doGet1(SourceHttp source, HttpClient client) {

        HttpRequest request = null;
//        HttpRequest request = HttpRequest.newBuilder(source.getUrl())
//                .GET()
//                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private static String doPost1(SourceHttp source, HttpClient client) {
        HttpRequest request = null;
//        HttpRequest request = HttpRequest.newBuilder(source.getUrl())
//                .POST(HttpRequest.BodyPublishers.ofString(source.getBody()))
//                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
