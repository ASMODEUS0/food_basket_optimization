package com.example.food_basket_optimization.importer;

import com.example.food_basket_optimization.importer.parser.Parser;

import com.example.food_basket_optimization.importer.parser.parsedobject.ParsedObject;
import com.example.food_basket_optimization.importer.parser.parsedproperties.HttpHtmlProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.concurrent.*;

@Component
@Getter
@Setter
public class Importer  {

    private PropertyChangeSupport support;

    @Autowired
    private Parser parser;

    @Autowired
    private   Map<Class<?>, List<?>> importContext;

    private final ExecutorService threadPool = Executors.newCachedThreadPool();

    @Autowired
    private  ImportConfiguration configuration;

    public Importer() {
        support = new PropertyChangeSupport(this);

    }

    public void initialImportResources(){



//        HttpHtmlProperties httpHtmlProperties = new HttpHtmlProperties();

        configuration.getPropertiesHttp().forEach(propertyHtpp -> {
            ParsedObject<?> parsedObject = new ParsedObject(this, propertyHtpp);
            ImportRunnable importRunnable = new ImportRunnable(parsedObject);
            support.addPropertyChangeListener(importRunnable);
            threadPool.execute(importRunnable);
        });



    }



}
