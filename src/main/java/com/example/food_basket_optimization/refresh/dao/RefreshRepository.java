package com.example.food_basket_optimization.refresh.dao;

import com.example.food_basket_optimization.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface RefreshRepository<K extends Serializable, E extends BaseEntity<K>> {

    E save(E entity);

    void delete(K id);

    void update(E entity);

    Optional<E> findById(K id);

    List<E> findAll();

}
