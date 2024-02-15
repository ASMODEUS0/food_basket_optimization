package com.example.food_basket_optimization.importer.parser.parsedproperties;

import java.util.List;
import java.util.Optional;

public interface Mapper {
   List<Object> map(List<? extends MapProperty> properties);
}
