package com.example.food_basket_optimization.extraction.service.request;

import com.example.food_basket_optimization.extraction.service.request.processing.ProcessingProxyCookie;
import com.example.food_basket_optimization.extraction.service.request.task.RequestTask;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.core5.http.Header;

import java.util.*;

import static java.util.AbstractMap.*;

public class HttpProxyClientProvider {

    private final List<SimpleEntry<HttpClientContainer, List<Header>>> httpProxiesWithProcessingCookie;
    private Integer currentClient = 0;

    public HttpProxyClientProvider(List<HttpProxyClient> proxyClients,
                                   List<ProcessingProxyCookie> processingList) {

        httpProxiesWithProcessingCookie = proxyClients.stream().map(client -> {

            List<Header> proxyClientHeaders = processingList.stream()
                    .map(processing -> processing.processing(client.getAddress()
                    , client.getPort()
                    , client.getLogin()
                    , client.getPassword()))
                    .flatMap(Collection::stream)
                    .toList();
            return new SimpleEntry<>(new HttpClientContainer(client.getClient()), proxyClientHeaders);
        }).toList();
    }



    public RequestTask getTask(HttpUriRequestBase request){
        SimpleEntry<HttpClientContainer, List<Header>> client = getClient(currentClient);
        client.getValue().forEach(request::addHeader);
        return new RequestTask(client.getKey(), request);
    }


    private SimpleEntry<HttpClientContainer, List<Header>> getClient(Integer currentClient){
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SimpleEntry<HttpClientContainer, List<Header>> clientWithHeaders = httpProxiesWithProcessingCookie.get(currentClient);
       if(clientWithHeaders.getKey().isInUse()){
          return getClient(incrementClient());
       }else{
           incrementClient();
           clientWithHeaders.getKey().use();
           return clientWithHeaders;
       }
    }

    private Integer incrementClient(){
        if(currentClient == httpProxiesWithProcessingCookie.size() - 1){
            currentClient = 0;
        }else{
            currentClient ++;
        }
        return currentClient;
    }

}
