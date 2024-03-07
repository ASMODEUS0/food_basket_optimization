package com.example.food_basket_optimization.extraction.properties.util;

import java.util.List;

/**
 * Notifies that the object that inherits this interface contains logic of its own reproduction.
 * The logic of reproduction depends on the object that inherits this interface.
 * The parameter T is the
 * @param <T>  The type of multiplied object
 */
public interface Multiplying<T> {
    /**
     * Multiply property depending on subclass behavior
     * @return propagated properties witch will be cast to T
     */
    List<? extends T> multiply();
}
