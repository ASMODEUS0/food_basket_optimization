package com.example.food_basket_optimization.importer.parser.parsedproperties;

import com.example.food_basket_optimization.importer.parser.parsedproperties.url.HttpMethod;
import com.example.food_basket_optimization.importer.parser.parsedproperties.url.KeyValueUrlBasic;
import com.example.food_basket_optimization.importer.parser.parsedproperties.url.KeyValueUrlContextual;
import com.example.food_basket_optimization.importer.parser.parsedproperties.url.KeyValueUrlProperty;
import com.example.food_basket_optimization.pojo.DiksiCity;
import lombok.*;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Setter
@Getter
public class SourceHttp extends SourceHttpAbs implements ParsedSourceContract{
   private final  List<KeyValueUrlProperty> params;
   private final  List<KeyValueUrlProperty> headers;


   public SourceHttp(URI uri, HttpMethod method, String body, List<KeyValueUrlProperty> params, List<KeyValueUrlProperty> headers) {
      super(uri, method, body);
      this.params = params;
      this.headers = headers;
   }

   @Override
   public List<Class<?>> getReferences() {
      ArrayList<KeyValueUrlProperty> keyValueUrlProperties = new ArrayList<>(params);
      keyValueUrlProperties.addAll(headers);

      ArrayList<Class<?>> references = new ArrayList<>();

      keyValueUrlProperties.stream().forEach(property ->{
         if(property instanceof KeyValueUrlContextual ){
            Class<?> refClass = ((KeyValueUrlContextual) property).getRefClass();
            references.add(refClass);
         }
      });

      return references;
   }
}
