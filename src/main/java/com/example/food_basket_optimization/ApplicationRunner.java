package com.example.food_basket_optimization;

import com.example.food_basket_optimization.importer.Importer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication()
public class ApplicationRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationRunner.class, args);

        Importer importer = context.getBean(Importer.class);
        importer.importAll();

    }

}
