package com.example.food_basket_optimization.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JavaType;
import org.hibernate.type.descriptor.java.BasicJavaType;

@Entity
public class Product {

    @Id
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Market shopType;
    private String name;
    private String price;
    @OneToOne
    private City city;
}
