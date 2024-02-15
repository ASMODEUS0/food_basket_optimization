package com.example.food_basket_optimization.importer.parser.parsedobject;

import java.util.List;
import java.util.Optional;

public interface ParsedObjectContract {

    Boolean parseIsPossible();
    List<Object> parse();
}
