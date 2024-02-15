package com.example.food_basket_optimization.importer;

import com.example.food_basket_optimization.importer.parser.parsedproperties.ParsedProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "config")
@Setter
@Getter
@RequiredArgsConstructor
public class ImportConfiguration {

    private final List<ParsedProperties> propertiesHttp;

//    private List<Resource> resources;

}
