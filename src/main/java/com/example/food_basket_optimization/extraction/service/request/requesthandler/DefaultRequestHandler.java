package com.example.food_basket_optimization.extraction.service.request.requesthandler;

import com.example.food_basket_optimization.extraction.service.request.HttpClientContainer;
import com.example.food_basket_optimization.extraction.service.request.HttpResponse;
import com.example.food_basket_optimization.extraction.service.request.RequestProvider;
import com.example.food_basket_optimization.extraction.service.request.components.DefaultRequestComponents;
import com.example.food_basket_optimization.extraction.service.request.executor.DefaultRequestExecutor;
import com.example.food_basket_optimization.extraction.service.request.processing.ProcessingRequest;
import com.example.food_basket_optimization.extraction.service.request.task.DefaultHttpRequestTaskResult;
import com.example.food_basket_optimization.extraction.service.request.task.RequestTask;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultRequestHandler implements RequestHandler {

    private final CloseableHttpClient client;
    private final DefaultRequestExecutor executor;
    private final List<ProcessingRequest> processingRequests = new ArrayList<>();

    public DefaultRequestHandler(DefaultRequestExecutor executor) {
        this.executor = executor;
        client = HttpClients.custom()
                .disableCookieManagement()
                .build();
    }

    @Override
    public List<String> doRequests(List<DefaultRequestComponents> componentsList) {

        List<HttpUriRequestBase> requests = componentsList.stream().map(RequestProvider::provideRequest).toList();

        return requests.stream()
                .map(request -> executor.execute(new RequestTask(new HttpClientContainer(client), request), processingRequests))
                .map(DefaultHttpRequestTaskResult::response)
                .map(HttpResponse::getContent)
                .toList();
    }

    public void addProcessingRequest(ProcessingRequest processingRequest){
        processingRequests.add(processingRequest);
    }


}
