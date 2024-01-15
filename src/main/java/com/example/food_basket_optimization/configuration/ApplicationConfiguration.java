package com.example.food_basket_optimization.configuration;


import com.example.food_basket_optimization.refresh.Refresh;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.food_basket_optimization.refresh.properties.RefreshProperties;

@Configuration
public class ApplicationConfiguration {



   @Bean
   @ConfigurationProperties("refreshproperties")
   public RefreshProperties refreshProperties(){
       return new RefreshProperties();
   }

   @Bean
    public ObjectMapper om(){
       return new ObjectMapper()
               .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
               .configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
   }

   @Bean
    public Refresh refresh(){
       return new Refresh();
    }


//   @Bean
//    public String importantString(){
//       return "shshhs";
//   }



}
