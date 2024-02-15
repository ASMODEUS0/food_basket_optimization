package com.example.food_basket_optimization;

import com.example.food_basket_optimization.importer.ImportConfiguration;
import com.example.food_basket_optimization.importer.Importer;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan
public class FoodBasketOptimizationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FoodBasketOptimizationApplication.class, args);





        ImportConfiguration bean = context.getBean(ImportConfiguration.class);
        Importer importer = context.getBean(Importer.class);

        importer.initialImportResources();




//        }



    }

}
