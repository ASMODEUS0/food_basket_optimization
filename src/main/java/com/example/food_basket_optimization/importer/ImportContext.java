package com.example.food_basket_optimization.importer;

import com.example.food_basket_optimization.importer.parser.Parser;
import com.example.food_basket_optimization.importer.parser.parsedobject.FileJsonParsedObject;
import com.example.food_basket_optimization.importer.parser.parsedobject.HTTPHtmlParsedObject;
import com.example.food_basket_optimization.importer.parser.parsedobject.HTTPJsonParsedObject;
import com.example.food_basket_optimization.importer.parser.parsedobject.ParsedObjectFactory;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Setter
public class ImportContext {

    @Autowired
    private  Parser parser;
    private List<FileJsonParsedObject> fileParseProperties;
    private List<HTTPJsonParsedObject> httpJsonParseObjects;
    private List<HTTPHtmlParsedObject> HTTPHtmlParsedObjects;

    private DiksiResource diksiResource;


    public boolean importAllResources(){
        ParsedObjectFactory parsedObjectFactory = new ParsedObjectFactory();
        diksiResource.getHtmlParsedObjects();

        parser.parseHtml(diksiResource.getHtmlParsedObjects());


        List<List<Object>> parsedObjects = fileParseProperties.stream().map(parser::parseJson).toList();
        List<List<Object>> parsedObjectsHttp = httpJsonParseObjects.stream().map(parser::parseJson).toList();


        return true;
    }






}
