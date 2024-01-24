package com.example.food_basket_optimization.importer;


import com.example.food_basket_optimization.importer.parser.parsedobject.HTTPHtmlParsedObject;
import com.example.food_basket_optimization.importer.parser.parsedobject.HtmlParsedObjectContract;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Setter
@Getter
public class DiksiResource{


    private List<Object> resourceParsedObjects;

    private List<HTTPHtmlParsedObject> httpHtmlParsedObjects;

    private List<String> strings;

    private String name;

}
