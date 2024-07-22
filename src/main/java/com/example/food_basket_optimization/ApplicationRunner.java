package com.example.food_basket_optimization;

import com.example.food_basket_optimization.entity.Store;
import com.example.food_basket_optimization.importer.Importer;
import com.example.food_basket_optimization.repository.StoreRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication()
public class ApplicationRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationRunner.class, args);
//        Importer importer = context.getBean(Importer.class);
//        importer.start();
//        StoreRepository storeRepository = new StoreRepository();
//
//        List<Store> all = storeRepository.getAll();

        System.out.println("");
//
    }
}
