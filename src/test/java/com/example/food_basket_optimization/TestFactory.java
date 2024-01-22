package com.example.food_basket_optimization;

import com.example.food_basket_optimization.importer.parser.parsedobject.HTTPHtmlParsedObject;
import com.example.food_basket_optimization.importer.parser.parsedobject.ParsedObjectFactory;
import com.example.food_basket_optimization.importer.parser.parsedobject.url.Header;
import com.example.food_basket_optimization.importer.parser.parsedobject.url.HeaderType;
import com.example.food_basket_optimization.importer.parser.parsedobject.url.HttpMethod;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestFactory {


    @Test
    public void testFactory() {
        Header header = new Header(HeaderType.ITERABLE, "PAGEN_1", "1-50");
        Header plainHeader = new Header(HeaderType.PLAIN, "PAGEN_1", "1");

        ArrayList<Header> headers = new ArrayList<>();
        headers.add(header);
        headers.add(plainHeader);

        HTTPHtmlParsedObject httpHtmlParsedObject = new HTTPHtmlParsedObject(headers,
                null,
                URI.create("https://dixy.ru/catalog/"),
                HttpMethod.GET,
                "");

        ParsedObjectFactory parsedObjectFactory = new ParsedObjectFactory();
        List<HTTPHtmlParsedObject> multiply = parsedObjectFactory.multiplyHeader(List.of(httpHtmlParsedObject));

        System.out.println("");


//        parsedObjectFactory.multiply()
    }
}
