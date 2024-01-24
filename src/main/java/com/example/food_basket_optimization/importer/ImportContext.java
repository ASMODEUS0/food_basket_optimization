package com.example.food_basket_optimization.importer;

import com.example.food_basket_optimization.importer.parser.Parser;
import com.example.food_basket_optimization.importer.parser.parsedobject.FileJsonParsedObject;
import com.example.food_basket_optimization.importer.parser.parsedobject.HTTPHtmlParsedObject;
import com.example.food_basket_optimization.importer.parser.parsedobject.HTTPJsonParsedObject;
import com.example.food_basket_optimization.importer.parser.parsedobject.ParsedObjectFactory;
import jdk.jfr.Event;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Setter
public class ImportContext {
    Map<Class<?>, List<?>> objectContext = new HashMap<>();




    @Autowired
    private Parser parser;
    private List<FileJsonParsedObject> fileParseProperties;
    private List<HTTPJsonParsedObject> httpJsonParseObjects;
    private List<HTTPHtmlParsedObject> HTTPHtmlParsedObjects;

    private DiksiResource diksiResource;


    public boolean importAllResources() {
        List<HTTPHtmlParsedObject> httpHtmlParsedObjects = ParsedObjectFactory.multiplyHeader(diksiResource.getHttpHtmlParsedObjects());

        List<Object> objects = parser.parseHtml(httpHtmlParsedObjects);


        List<List<Object>> parsedObjects = fileParseProperties.stream().map(parser::parseJson).toList();
        List<List<Object>> parsedObjectsHttp = httpJsonParseObjects.stream().map(parser::parseJson).toList();


        return true;
    }


}
