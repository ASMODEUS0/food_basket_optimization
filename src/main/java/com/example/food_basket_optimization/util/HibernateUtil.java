package com.example.food_basket_optimization.util;

import com.example.food_basket_optimization.dao.Store;
import com.example.food_basket_optimization.entity.City;

import com.example.food_basket_optimization.entity.Shop;
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
//        configuration.addAnnotatedClass(DiksiProduct.class);
//        configuration.addAnnotatedClass(DiksiCity.class);
        configuration.addAnnotatedClass(Shop.class);
        return configuration;


    }

}
