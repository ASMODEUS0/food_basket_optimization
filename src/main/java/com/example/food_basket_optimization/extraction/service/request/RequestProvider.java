package com.example.food_basket_optimization.extraction.service.request;

import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.HttpMethod;
import com.example.food_basket_optimization.extraction.service.request.components.DefaultRequestComponents;
import com.example.food_basket_optimization.extraction.service.request.components.GetRequestComponents;
import com.example.food_basket_optimization.extraction.service.request.components.PostRequestComponents;
import com.example.food_basket_optimization.extraction.service.request.util.RequestUtil;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.core5.http.io.entity.BasicHttpEntity;
import org.apache.hc.core5.http.message.BasicHeader;

public class RequestProvider {
    public static HttpUriRequestBase provideRequest(DefaultRequestComponents components) {
        switch (components.getMethod()) {

            case GET -> {
                return provideGETRequest(components);
            }
            case POST -> {
                return providePOSTRequest(components);

            }
            // TODO: 17.04.2024 put method
            case PUT -> throw new IllegalArgumentException("PUT method has not yet been implemented");

            // TODO: 17.04.2024 put method
            case DELETE -> throw new IllegalArgumentException("DELETE method has not yet been implemented");

            default -> throw new IllegalArgumentException(components.getMethod() + "method has not yet been implemented");
        }
    }

    private static HttpUriRequestBase providePOSTRequest(PostRequestComponents components) {
        HttpUriRequestBase request = new HttpUriRequestBase(HttpMethod.POST.getMethodName(), components.getUri());
        request.setEntity(new BasicHttpEntity(components.getBodyStream(), components.getContentType()));
        request.setHeaders(RequestUtil.headers(components.getHeaders()).toArray(new BasicHeader[]{}));
        return request;
    }


    private static HttpUriRequestBase provideGETRequest(GetRequestComponents components) {
        HttpUriRequestBase request = new HttpUriRequestBase(HttpMethod.GET.getMethodName(), components.getUri());
        request.setHeaders(RequestUtil.headers(components.getHeaders()).toArray(new BasicHeader[]{}));
        return request;
    }


}
