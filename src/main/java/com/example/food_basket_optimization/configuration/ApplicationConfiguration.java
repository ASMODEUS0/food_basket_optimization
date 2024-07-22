package com.example.food_basket_optimization.configuration;



import com.example.food_basket_optimization.extraction.service.request.*;
import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.service.request.executor.DefaultRequestExecutor;
import com.example.food_basket_optimization.extraction.service.request.requesthandler.ProxyRequestHandler;
import com.example.food_basket_optimization.extractpojo.extractedentity.lenta.LentaNodeCodeExtr;
import org.apache.hc.core5.http.HttpHost;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


@Configuration
public class ApplicationConfiguration {

    //todo: A context must be an object.
    @Bean
    public ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext(List<LentaNodeCodeExtr> lentaNodeCodes) {
        ConcurrentHashMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractedContext = new ConcurrentHashMap<>();
        extractedContext.put(LentaNodeCodeExtr.class, lentaNodeCodes);
        return extractedContext;
    }

    @Bean
    public List<HttpProxyClient> proxyClients(){
//        HttpProxyClient pc1 = new HttpProxyClient(new HttpHost("212.81.38.180", 9829), "FTuwPy", "HR0tdc");
//        HttpProxyClient pc2 = new HttpProxyClient(new HttpHost("212.81.36.43", 9026), "FTuwPy", "HR0tdc");
//        HttpProxyClient pc3 = new HttpProxyClient(new HttpHost("212.81.37.243", 9271), "FTuwPy", "HR0tdc");

//        HttpProxyClient pc4 = new HttpProxyClient(new HttpHost("194.67.220.76", 9448), "d8xEht", "xGee1k");

        HttpProxyClient pc5 = new HttpProxyClient(new HttpHost("147.45.55.141", 9220), "8aUe7Z", "eyqfBR");
        HttpProxyClient pc6 = new HttpProxyClient(new HttpHost("46.161.45.148", 9992), "8aUe7Z", "eyqfBR");
        HttpProxyClient pc7 = new HttpProxyClient(new HttpHost("188.119.127.207", 9649), "8aUe7Z", "eyqfBR");
        HttpProxyClient pc8 = new HttpProxyClient(new HttpHost("147.45.120.124", 9393), "8aUe7Z", "eyqfBR");
        HttpProxyClient pc9 = new HttpProxyClient(new HttpHost("147.45.120.110", 9898), "8aUe7Z", "eyqfBR");
        HttpProxyClient pc10 = new HttpProxyClient(new HttpHost("147.45.123.178", 9755), "8aUe7Z", "eyqfBR");


        ArrayList<HttpProxyClient> clients = new ArrayList<>();
//        clients.add(pc1);
//        clients.add(pc2);
//        clients.add(pc3);
//        clients.add(pc4);
        clients.add(pc5);
        clients.add(pc6);
        clients.add(pc7);
        clients.add(pc8);
        clients.add(pc9);
        clients.add(pc10);
        return clients;
    }


    @Bean
    public HttpProxyClientProvider proxyClientProvider(List<HttpProxyClient> proxyClients){
       return new HttpProxyClientProvider(proxyClients, List.of());
    }


    @Bean
    public ProxyRequestHandler requestHandler(DefaultRequestExecutor executor, HttpProxyClientProvider proxyClientProvider) {
        return new ProxyRequestHandler(executor, proxyClientProvider);
    }

}
