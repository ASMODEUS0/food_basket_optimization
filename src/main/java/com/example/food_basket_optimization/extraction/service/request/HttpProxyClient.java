package com.example.food_basket_optimization.extraction.service.request;

import lombok.Getter;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpHost;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Getter
public class HttpProxyClient {

    private final CloseableHttpClient client;
    private final String address;
    private final Integer port;
    private final String login;
    private final String password;



    public HttpProxyClient(HttpHost proxyHost, String login, String password){
        Objects.requireNonNull(proxyHost);

        this.address =  proxyHost.getHostName();
        this.port = proxyHost.getPort();
        this.login = login;
        this.password = password;

        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(proxyHost.getHostName(), proxyHost.getPort()),
                new UsernamePasswordCredentials(login, password.toCharArray())
        );

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
        client = HttpClients.custom()
                .setProxy(proxyHost)
                .setDefaultRequestConfig(requestConfig)
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();
    }
}
