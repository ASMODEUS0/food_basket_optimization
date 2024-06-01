package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url;

import com.example.food_basket_optimization.extraction.properties.SimplePropertyAbs;
import lombok.Getter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Getter
public abstract class UrlPropertiesAbs extends SimplePropertyAbs<URL> {
    private final String protocol;
    private final String path;
    private final String host;
    private URL url;



    protected UrlPropertiesAbs(String protocol, String host, String path) {
        this.protocol = protocol;
        this.path = path;
        this.host = host;


    }


    protected static String createPathFromPathElements(List<String> pathElements) {
        StringBuffer sbPath = new StringBuffer();
        pathElements.forEach(element -> sbPath.append("/").append(element));
        return sbPath.toString();
    }


    public URL getUrl() {
        if(url != null){
            return url;
        }else{
            try {
                URL url = new URL(getProtocol(), getHost(), path);
                this.url = url;
                return url;
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
