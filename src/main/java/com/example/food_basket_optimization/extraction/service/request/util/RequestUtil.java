package com.example.food_basket_optimization.extraction.service.request.util;

import com.example.food_basket_optimization.extraction.service.request.task.DefaultHttpRequestTaskResult;
import com.example.food_basket_optimization.extraction.service.request.HttpResponse;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.message.BasicHeader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class RequestUtil {

    public static List<BasicHeader> headers(List<String> headersStr){

        ArrayList<BasicHeader> result = new ArrayList<>();
        if(headersStr == null){
            throw new IllegalArgumentException("headers must not be null");
        }

        if(headersStr.size() % 2 != 0){
            throw  new IllegalArgumentException("header must contain name and value attribute");
        }

        for(int i = 0; i < headersStr.size(); i+= 2){
            result.add(new BasicHeader(headersStr.get(i), headersStr.get(i + 1)));
        }
        return result;
    }

    public static List<Header> getHeaders(String withName, HttpUriRequestBase request){
        List<Header> headers = Arrays.stream(request.getHeaders()).toList();
        return headers.stream().filter(header -> header.getName().equalsIgnoreCase(withName)).toList();
    }


    public static List<HttpResponse> waitingForCompletion(List<Future<DefaultHttpRequestTaskResult>> futures) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (Future<DefaultHttpRequestTaskResult>future : futures) {
            if (!future.isDone()) {
                return waitingForCompletion(futures);
            }
        }
        return tryToUnwrap(futures);
    }



    public static List<HttpResponse> tryToUnwrap(List<Future<DefaultHttpRequestTaskResult>> futures) {
        return futures.stream().map(future -> {
            try {
                DefaultHttpRequestTaskResult defaultHttpRequestTaskResults = future.get();
                return defaultHttpRequestTaskResults.response();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

}
