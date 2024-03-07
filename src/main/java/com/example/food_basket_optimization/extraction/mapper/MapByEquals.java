package com.example.food_basket_optimization.extraction.mapper;

import com.example.food_basket_optimization.extraction.ExtractedEntity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MapByEquals {

    Class<? extends ExtractedEntity> unionClazz();
    String ownField();
    String unionField();
}
