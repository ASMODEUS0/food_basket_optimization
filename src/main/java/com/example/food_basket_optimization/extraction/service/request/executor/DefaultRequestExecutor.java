package com.example.food_basket_optimization.extraction.service.request.executor;

import com.example.food_basket_optimization.extraction.service.request.*;
import com.example.food_basket_optimization.extraction.service.request.processing.ProcessingRequest;
import com.example.food_basket_optimization.extraction.service.request.responsehandler.HttpClientResponseHandler;
import com.example.food_basket_optimization.extraction.service.request.task.DefaultHttpRequestTaskResult;
import com.example.food_basket_optimization.extraction.service.request.task.RequestTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class DefaultRequestExecutor {

    public DefaultHttpRequestTaskResult execute(RequestTask task, List<ProcessingRequest> processingRequests){
        HttpUriRequestBase request = task.getRequest();
        HttpClientContainer httpClientContainer = task.getClientContainer();
        CloseableHttpClient client = httpClientContainer.getClient();
        processingRequests.forEach(processing -> processing.processing(client, request));
        try {
            httpClientContainer.free();
            HttpResponse response = client.execute(request, new HttpClientResponseHandler());
            log.info("Task: "+task.hashCode()+" Send "+request.getMethod()+" request with URI: " + request.getRequestUri());
            return new DefaultHttpRequestTaskResult(client, request, response);
        } catch (IOException e) {
            httpClientContainer.free();
            log.info("Task: "+task.hashCode()+" fail "+request.getMethod()+" request with URI: " + request.getRequestUri());
            return new DefaultHttpRequestTaskResult(client, request, new HttpResponse("ERROR", 0));
        }
    }



}
