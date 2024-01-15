package com.example.food_basket_optimization;

import com.example.food_basket_optimization.dao.CityRepository;
import com.example.food_basket_optimization.mapper.CityReadMapper;
import com.example.food_basket_optimization.refresh.Refresh;
import com.example.food_basket_optimization.refresh.properties.RefreshProperties;
import com.example.food_basket_optimization.service.CityService;
import com.example.food_basket_optimization.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SpringBootApplication
@ConfigurationPropertiesScan
public class FoodBasketOptimizationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FoodBasketOptimizationApplication.class, args);

        Refresh refresh = context.getBean("refresh", Refresh.class);
        refresh.refresh(context);


//        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();) {
//
//            Session session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class}, (proxy, method, args1) ->
//                method.invoke(sessionFactory.getCurrentSession(), args1));
//
//
//            session.beginTransaction();
//
//
//
//            CityRepository cityRepository = new CityRepository(session);
//            CityService cityService = new CityService(cityRepository, new CityReadMapper());
//
//            cityService.findById(1L).ifPresent(System.out::println);
//
//            session.getTransaction().commit();
//
//        }



    }

}
