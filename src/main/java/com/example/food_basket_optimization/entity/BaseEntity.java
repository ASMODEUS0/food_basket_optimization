package com.example.food_basket_optimization.entity;

import java.io.Serializable;

public interface  BaseEntity<T extends Serializable> {

    void setId(T id);

    T getId();

}
