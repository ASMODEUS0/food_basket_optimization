package com.example.food_basket_optimization.repository;

import com.example.food_basket_optimization.dto.StoreDto;
import com.example.food_basket_optimization.entity.Store;
import com.example.food_basket_optimization.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.List;

@Component
public class StoreRepository {

    public StoreRepository() {

    }

    public List<StoreDto> getAll() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            var criteria = session.getCriteriaBuilder().createQuery(Store.class);
            criteria.from(com.example.food_basket_optimization.entity.Store.class);
            List<Store> stores = session.createQuery(criteria).getResultList();

            return stores.stream().map(store -> new StoreDto(store.getAddress()
                    , store.getLatitude()
                    , store.getLongitude()
                    , store.getShopType())).toList();
        }
    }
}
