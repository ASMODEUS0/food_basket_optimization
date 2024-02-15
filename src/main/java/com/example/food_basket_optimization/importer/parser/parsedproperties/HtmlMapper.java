package com.example.food_basket_optimization.importer.parser.parsedproperties;

import com.example.food_basket_optimization.importer.parser.mapping.Attribute;
import com.example.food_basket_optimization.importer.parser.mapping.Contextual;
import com.example.food_basket_optimization.importer.parser.mapping.Select;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HtmlMapper implements Mapper {


    public HtmlMapper() {
    }

    @Override
    public List<Object> map(List<? extends MapProperty> properties) {
//        Optional<List<Object>> entities = Optional.of(new ArrayList<>());
       return properties.stream().flatMap(property -> {
          return readValue(property).stream();
       }).toList();
    }


    public List<Object> readValue(MapProperty property) {
        return map(property);
    }

    public List<Object> map(MapProperty property) {
        Document document = Jsoup.parse(property.getData());

        if (property.getClassToParse().isAnnotationPresent(Select.class)) {
            String value = property.getClassToParse().getAnnotation(Select.class).value();
            Elements rootElements = document.select(value);
            List<Object> objects = creatObjects(rootElements, property.getClassToParse());

            Field[] fields = property.getClassToParse().getDeclaredFields();

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
                    } else if (Contextual.class.equals(annotation.annotationType())) {
                        Object extractedEntity = extractContextualEntity(property.getReferenceEntities(), (Contextual) annotation, field);
                        objects = setContextualEntity(objects, extractedEntity, field);
                    } else {
                        throw new IllegalArgumentException();
                    }

                }


            }

            return objects;

        }

        return null;

    }

    private List<Object> setContextualEntity(List<Object> objects, Object entity, Field field) {
        return objects.stream().map(object -> {
            try {
                field.setAccessible(true);
                field.set(object, entity);
                return object;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    //todo: rebuild;
    private Object extractContextualEntity(Map<String, Object> refEntities, Contextual annotation, Field field) {
        return refEntities.getOrDefault(annotation.key(), null);
    }


    public List<String> selectExtract(Elements elements, Select annotation) {
        return elements.stream().map(element -> element.select(annotation.value()).first().text()).toList();
    }

    public List<String> attributeExtract(Elements elements, Attribute annotation) {
        String value = annotation.value();
        return elements.stream().map(element -> element.attr(value)).toList();
    }


    private List<Object> creatObjects(Elements elements, Class<?> clazz) {
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


    private List<Object> setValues(List<Object> objects, List<String> values, Field field) {

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
