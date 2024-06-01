package com.example.food_basket_optimization.extraction;

import com.example.food_basket_optimization.extraction.tool.ExtractCallable;
import com.example.food_basket_optimization.extraction.tool.extractobject.ExtractObject;
import com.example.food_basket_optimization.extraction.properties.root.ExtractionProperties;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.concurrent.*;

@Getter
@Setter
@Component
public class Extractor implements ExtractRuler {

    private final PropertyChangeSupport support;

    private final ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext;

    private final ExecutorService threadPool = Executors.newCachedThreadPool();

    private final ExtractConfiguration extractConfiguration;


    public Extractor(ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext,
                     ExtractConfiguration extractConfiguration) {
        this.extractContext = extractContext;
        this.extractConfiguration = extractConfiguration;
        support = new PropertyChangeSupport(this);

    }


    public <T extends ResolvableSource<?>> Future<List<? extends ExtractedEntity>> extract(ExtractionProperties<T> properties) {
        ExtractObject<T> extractObject = new ExtractObject<>(this, properties);
        ExtractCallable extractCallable = new ExtractCallable(extractObject);
        addExtractListener(extractCallable);
        return threadPool.submit(extractCallable);
    }

    public List<Future<List<? extends ExtractedEntity>>> extract(List<ExtractionProperties<?>> properties) {
        return properties.stream().map(this::extract).toList();
    }


    public List<Future<List<? extends ExtractedEntity>>> extract() {
        if (extractConfiguration == null) {
            throw new IllegalStateException("Properties configuration is null");
        }
        return extract(extractConfiguration.getPropertiesHttp());
    }


    private void addExtractListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }


    @Override
    public PropertyChangeSupport getChangeSupport() {
        return support;
    }
}
