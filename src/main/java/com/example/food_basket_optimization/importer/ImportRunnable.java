package com.example.food_basket_optimization.importer;

import com.example.food_basket_optimization.importer.parser.parsedobject.ParsedObject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.LockSupport;

public class ImportRunnable implements Runnable, PropertyChangeListener {


    private final ParsedObject<?> parsedObject;
    private  Thread thread;


    public ImportRunnable(ParsedObject<?> parsedObject) {
        this.parsedObject = parsedObject;

    }

    @Override
    public void run() {
        thread = Thread.currentThread();

        while(true){
            if (parsedObject.parseIsPossible()) {
                List<Object> parse = parsedObject.parse();
                break;
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
