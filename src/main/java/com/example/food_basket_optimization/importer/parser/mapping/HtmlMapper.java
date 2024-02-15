package com.example.food_basket_optimization.importer.parser.mapping;

import com.example.food_basket_optimization.pojo.DiksiCity;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class HtmlMapper  {

    public static Optional<List<Object>> readValue(String html, Class<?> clazz) {
        Document document = Jsoup.parse(html);

        return map(document, clazz);
    }

    public static Optional<List<Object>> map(Document document, Class<?> clazz) {

        if (clazz.isAnnotationPresent(Select.class)) {
            String value = clazz.getAnnotation(Select.class).value();
            Elements rootElements = document.select(value);
            List<Object> objects = creatObjects(rootElements, clazz);


            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                Annotation[] annotations = field.getAnnotations();

                for (Annotation annotation : annotations) {
                    if (Select.class.equals(annotation.annotationType())) {
                        List<String> extractedValues = selectExtract(rootElements, (Select) annotation);
                        objects = setValues(objects, extractedValues, field);
                        break;
                    } else if (Attribute.class.equals(annotation.annotationType())) {
                        List<String> extractedValues = attributeExtract(rootElements, (Attribute) annotation);
                        objects = setValues(objects, extractedValues, field);
                        break;
                    } else {
                        throw new IllegalArgumentException();
                    }

                }


            }

            return Optional.ofNullable(objects);

        }

        return Optional.empty();

    }


    public static List<String> selectExtract(Elements elements, Select annotation) {
        String value = annotation.value();
        Elements extractedElements = elements.select(value);
        return extractedElements.stream().map(Element::text).toList();
    }

    public static List<String> attributeExtract(Elements elements, Attribute annotation) {
        String value = annotation.value();
        return elements.stream().map(element -> element.attr(value)).toList();
    }


    private static List<Object> creatObjects(Elements elements, Class<?> clazz) {
        int count = elements.size();
        ArrayList<Object> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            try {
                Object o = clazz.getConstructor(null).newInstance(null);
                result.add(o);
            } catch (InstantiationException | InvocationTargetException | NoSuchMethodException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }


    private static List<Object> setValues(List<Object> objects, List<String> values, Field field) {

        List<Object> result = new ArrayList<>();
        if (objects.size() == values.size()) {

            for (int i = 0; i < objects.size(); i++) {
                try {
                    Object o = objects.get(i);
                    String s = values.get(i);
                    field.setAccessible(true);
                    field.set(o, s);
                    result.add(o);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            }
            return result;

        } else {
            throw new IllegalArgumentException();
        }

    }


}
