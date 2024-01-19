package com.example.food_basket_optimization.importer;


import com.example.food_basket_optimization.importer.parser.parsedobject.HTMLParsedObject;
import com.example.food_basket_optimization.importer.parser.parsedobject.HtmlParsedObjectContract;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Setter
@Getter
public class DiksiResource implements BaseResource {


    private List<Object> resourceParsedObjects;

    private List<HTMLParsedObject> htmlParsedObjects;

    private String name;


    @Override
    public List<? extends HtmlParsedObjectContract> getHtmlParsedObjects() {
        return htmlParsedObjects;
    }

}
