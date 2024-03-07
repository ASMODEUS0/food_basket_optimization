package com.example.food_basket_optimization.extraction.properties.source.sourcehttp;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.HttpMethod;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueUrlBasic;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueUrlBasicProperty;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class SourceHttp implements SourceHttpContract {

   private  final URL url;
   private final HttpMethod method;
   private final  String body;
   private final List<KeyValueUrlBasic> params;
   private final List<KeyValueUrlBasic> headers;
   private final List<? extends ExtractedEntity> refEntities;

   public SourceHttp(URL url,
                     HttpMethod method,
                     String body,
                     List<KeyValueUrlBasic> params,
                     List<KeyValueUrlBasic> headers,
                     List<? extends ExtractedEntity> refEntities) {

      this.url = url;
      this.method = method;
      this.body  = body;
      this.params = params;
      this.headers = headers;
      this.refEntities = refEntities;


   }


   @Override
   public List<KeyValueUrlBasic> getParams() {
      return params;
   }

   @Override
   public List<KeyValueUrlBasic> getHeaders() {
      return headers;
   }

   @Override
   public URL getUrl() {
      return this.url;
   }

   @Override
   public HttpMethod getMethod() {
      return method;
   }

   @Override
   public String getBody() {
      return body;
   }

   @Override
   public List<? extends ExtractedEntity> getRefProperties() {
      return null;
   }
}
