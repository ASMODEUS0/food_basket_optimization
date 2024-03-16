package com.example.food_basket_optimization.dao;

import com.example.food_basket_optimization.entity.City;
import jakarta.persistence.EntityManager;


public class CityRepository extends RepositoryBase<Long, City> {


    public CityRepository(EntityManager entityManager) {
        super(City.class, entityManager);
    }


}