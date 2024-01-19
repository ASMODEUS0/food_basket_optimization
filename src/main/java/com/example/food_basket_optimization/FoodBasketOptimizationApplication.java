package com.example.food_basket_optimization;

import com.example.food_basket_optimization.importer.ImportContext;
import com.example.food_basket_optimization.pojo.StoreFromMegaMarket;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan
public class FoodBasketOptimizationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FoodBasketOptimizationApplication.class, args);



        ImportContext bean = context.getBean(ImportContext.class);
        bean.importAllResources();


        System.out.println(bean);


//        }



    }

}
