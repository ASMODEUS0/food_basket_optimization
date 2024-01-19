package com.example.food_basket_optimization.configuration;


import com.example.food_basket_optimization.importer.ImportContext;
import org.hibernate.SessionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {


    @Bean
    @ConfigurationProperties("import-context")
    public ImportContext importContext() {

     return new ImportContext();
    }


//   @Bean
//    public String importantString(){
//       return "shshhs";
//   }


}
