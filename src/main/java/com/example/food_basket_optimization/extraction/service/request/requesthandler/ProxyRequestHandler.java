package com.example.food_basket_optimization.extraction.service.request.requesthandler;

import com.example.food_basket_optimization.extraction.service.request.components.DefaultRequestComponents;
import com.example.food_basket_optimization.extraction.service.request.*;
import com.example.food_basket_optimization.extraction.service.request.executor.DefaultRequestExecutor;
import com.example.food_basket_optimization.extraction.service.request.processing.ProcessingRequest;
import com.example.food_basket_optimization.extraction.service.request.task.DefaultHttpRequestTaskResult;
import com.example.food_basket_optimization.extraction.service.request.task.RequestTask;
import com.example.food_basket_optimization.extraction.service.request.util.RequestUtil;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RequiredArgsConstructor
public class ProxyRequestHandler implements RequestHandler {


    private final DefaultRequestExecutor requestExecutor;
    private final HttpProxyClientProvider provider;
    private final List<ProcessingRequest> processingRequests = new ArrayList<>();
    private final ExecutorService threadPool = Executors.newCachedThreadPool();


    @Override
    public List<String> doRequests(List<DefaultRequestComponents> componentsList) {
        List<HttpUriRequestBase> requests = componentsList.stream()
                .map(RequestProvider::provideRequest)
                .toList();

        List<Future<DefaultHttpRequestTaskResult>> responsesFuture = requests.stream()
                .map(request -> {
                    RequestTask task = provider.getTask(request);
                    Callable<DefaultHttpRequestTaskResult> callable = () -> requestExecutor.execute(task, processingRequests);
                    return threadPool.submit(callable);
                })
                .toList();

        List<HttpResponse> httpResponses = RequestUtil.waitingForCompletion(responsesFuture);
        return httpResponses.stream()
                .map(HttpResponse::getContent)
                .toList();

    }


    public void addProcessingRequest(ProcessingRequest processingRequest) {
        this.processingRequests.add(processingRequest);
    }
}
