package com.example.food_basket_optimization.extraction.tool;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.tool.extractobject.ExtractObject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.LockSupport;

public class ExtractCallable implements Callable<List<? extends ExtractedEntity>>, PropertyChangeListener {

    private final ExtractObject<?> extractObject;
    private Thread thread;


    public ExtractCallable(ExtractObject<?> extractObject) {
        this.extractObject = extractObject;

    }

    @Override
    public List<? extends ExtractedEntity> call() throws Exception {
        thread = Thread.currentThread();
        while (true) {
            if (extractObject.parseIsPossible()) {
                return extractObject.parse();
            } else {
                LockSupport.park();
            }
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LockSupport.unpark(thread);
    }



}
