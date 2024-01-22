package com.example.food_basket_optimization.importer.parser.parsedobject;

import com.example.food_basket_optimization.importer.parser.parsedobject.url.Header;
import com.example.food_basket_optimization.importer.parser.parsedobject.url.HeaderType;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ParsedObjectFactory {


    public static  <T extends HttpObject & Copy<T, HttpObject>> List<T> multiplyHeader(List<T> objects) {


        List<T> result = new ArrayList<>();
        for (T object : objects) {

            List<Header> headers = object.getHeaders();
            for (Header header : headers) {

                switch (header.type()) {
                    case PLAIN -> result.add(object);

                    case ITERABLE -> result.addAll(iterate(object, header));

                    default -> throw new IllegalStateException("Unexpected value: " + header.type());
                }
            }
        }
        return result;
    }


    private static <T extends HttpObject & Copy<T, HttpObject>> List<T> iterate(T object, Header header) {
        List<T> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("^(\\d+)-(\\d+)");
//        result.add(object);

        String value = header.value();
        if (pattern.matcher(value).find()) {
            String[] split = value.split("-");

            if (split.length == 2) {
                int value1 = Integer.parseInt(split[0]);
                int value2 = Integer.parseInt(split[1]);

                if (value1 < value2) {

                    for (int i = value1; i <= value2; i++) {
                        Header iterableHeader = new Header(HeaderType.PLAIN, header.key(), String.valueOf(i));
                        List<Header> headersCurrent = new ArrayList<>(object.getHeaders());
                        headersCurrent.remove(header);
                        headersCurrent.add(iterableHeader);
                        HttpObject httpObject = new HttpObject(headersCurrent, object.getUri(), object.getHttpMethod(), object.getBody(), object.getParams());
                        T copy = object.getCopy(httpObject);
                        result.add(copy);
                    }


                }


            }


        }


        return result;
    }



}
