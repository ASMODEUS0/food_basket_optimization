package com.example.food_basket_optimization.util;

import com.example.food_basket_optimization.entity.City;

import com.example.food_basket_optimization.entity.Product;
import com.example.food_basket_optimization.entity.Store;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
    public static SessionFactory buildSessionFactory(){
        Configuration configuration = buildConfiguration();
        configuration.configure();
        return configuration.buildSessionFactory();
    }

    private static Configuration buildConfiguration(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(City.class);
        configuration.addAnnotatedClass(Store.class);
        configuration.addAnnotatedClass(Product.class);
        return configuration;
    }

}
