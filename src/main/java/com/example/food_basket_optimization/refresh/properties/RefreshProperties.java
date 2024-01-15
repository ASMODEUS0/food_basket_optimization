package com.example.food_basket_optimization.refresh.properties;

import com.example.food_basket_optimization.refresh.parser.properties.FileParseProperties;
import com.example.food_basket_optimization.refresh.parser.properties.htpp.JsonParseProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class RefreshProperties {

    private List<String> dbScheme = new ArrayList<>();

    private List<FileParseProperties> fileParseProperties;

    private List<JsonParseProperties> jsonsParseProperties;

}
