package com.example.food_basket_optimization.extraction.properties.base.multi;

import java.util.List;

/**
 * Notifies that the object that inherits this interface contains logic of its own reproduction.
 * The logic of reproduction depends on the object that inherits this interface.
 * @param <T>  This is the superclass of the type into which the inherited object is propagated
 */
public interface Multiplying<T> {
    /**
     * Multiply property depending on subclass behavior
     * @return propagated properties witch will be cast to T
     */
    List<T> multiply();
}
