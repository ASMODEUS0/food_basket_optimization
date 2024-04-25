package com.example.food_basket_optimization.extraction.service.request.components;

import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.HttpMethod;
import com.example.food_basket_optimization.extraction.service.request.util.URIConstructor;
import lombok.Getter;
import org.apache.hc.core5.http.ContentType;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.List;

@Getter
public class DefaultRequestComponents implements PostRequestComponents, GetRequestComponents {
    private final URL url;
    private final List<String> params;
    private final List<String> headers;
    private final HttpMethod method;
    private final InputStream bodyStream;
    private final ContentType contentType;

    public DefaultRequestComponents(URL url,
                                    List<String> params,
                                    List<String> headers,
                                    HttpMethod method,
                                    InputStream bodyStream,
                                    ContentType contentType) {
        this.url = url;
        this.params = params;
        this.headers = headers;
        this.method = method;
        this.bodyStream = bodyStream;
        this.contentType = contentType;
    }

    @Override
    public URI getURI() {
       return URIConstructor.convert(url, params);
    }
}
