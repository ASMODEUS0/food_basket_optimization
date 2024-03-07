package com.example.food_basket_optimization.extraction.properties.mapping.htmlmap;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.MapProperty;
import com.example.food_basket_optimization.extraction.properties.mapping.Mapper;
import com.example.food_basket_optimization.extraction.properties.mapping.htmlmap.annotation.Attribute;
import com.example.food_basket_optimization.extraction.properties.mapping.htmlmap.annotation.Contextual;
import com.example.food_basket_optimization.extraction.properties.mapping.htmlmap.annotation.OppositeSide;
import com.example.food_basket_optimization.extraction.properties.mapping.htmlmap.annotation.Select;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Component
public class HtmlMapper implements Mapper {

    @Override
    public List <ExtractedEntity> map(List<? extends MapProperty> properties) {
      return properties.stream()
              .flatMap(property -> map(property).stream())
              .flatMap(Collection::stream)
              .toList();
    }



    public Optional<List<ExtractedEntity>> map(MapProperty property) {
        Document document = Jsoup.parse(property.getData());

        if (property.getClassToMap().isAnnotationPresent(Select.class)) {
            String value = property.getClassToMap().getAnnotation(Select.class).value();
            
            //Selecting all elements that corresponds to a select value and creates empty
            // extracted objects.
            Elements rootElements = document.select(value);
            List<ExtractedEntity> emtyExtractedObjects = creatObjects(rootElements, property.getClassToMap());

            if (emtyExtractedObjects.isEmpty()) {
                System.out.println("Root select don't get result");
                return Optional.empty();
            }

            List<ExtractedEntity> extractedObjects = new ArrayList<>(emtyExtractedObjects);
            Field[] fields = property.getClassToMap().getDeclaredFields();



            for (Field field : fields) {
                for (Annotation annotation : field.getAnnotations()) {
                    if (Select.class.equals(annotation.annotationType())) {
                        List<String> extractedValues = selectExtract(rootElements, (Select) annotation);
                        extractedObjects = setValues(extractedObjects, extractedValues, field);
                        break;
                    } else if (Attribute.class.equals(annotation.annotationType())) {
                        List<String> extractedValues = attributeExtract(rootElements, (Attribute) annotation);
                        extractedObjects = setValues(extractedObjects, extractedValues, field);
                        break;
                    } else if (Contextual.class.equals(annotation.annotationType())) {
//                        Object extractedEntity = extractContextualEntity(property.getReferenceEntities(), (Contextual) annotation, field);
//                        extractedObjects = setContextualEntity(extractedObjects, extractedEntity, field);
                    }
                }


            }

            return Optional.of(extractedObjects);

        }

        return Optional.empty();

    }

    private List<ExtractedEntity> setContextualEntity(List<ExtractedEntity> extractedObjects, Object entity, Field field) {
        ArrayList<ExtractedEntity> result = new ArrayList<>();

        for (ExtractedEntity extracted : extractedObjects) {
            try {
                field.setAccessible(true);
                field.set(extracted, entity);
                result.add(extracted);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        if (field.isAnnotationPresent(OppositeSide.class)) {
            OppositeSide annotation = field.getAnnotation(OppositeSide.class);
            setOppositeSide(extractedObjects, entity, annotation);
        }

        return result;

    }


    private void setOppositeSide(List<ExtractedEntity> extractedObjects, Object entity, OppositeSide annotation) {
        try {
            Method method = entity.getClass().getMethod(annotation.methodName(), extractedObjects.get(0).getClass());
            for (ExtractedEntity extractedObject : extractedObjects) {
                method.invoke(entity, extractedObject);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);

        }
    }

    private Object extractContextualEntity(Map<String, ExtractedEntity> refEntities, Contextual annotation, Field field) {
        return refEntities.getOrDefault(annotation.key(), null);
    }


    public List<String> selectExtract(Elements elements, Select annotation) {
        return elements.stream().map(element -> element.select(annotation.value()).first().text()).toList();
    }

    public List<String> attributeExtract(Elements elements, Attribute annotation) {
        String value = annotation.value();
        return elements.stream().map(element -> element.attr(value)).toList();
    }


    private List<ExtractedEntity> creatObjects(Elements elements, Class<? extends ExtractedEntity> clazz) {
        int count = elements.size();
        ArrayList<ExtractedEntity> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            try {
                ExtractedEntity o = (ExtractedEntity) clazz.getConstructor(null).newInstance(null);
                result.add(o);
            } catch (InstantiationException | InvocationTargetException | NoSuchMethodException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }


    private List<ExtractedEntity> setValues(List<ExtractedEntity> extractedObjects, List<String> values, Field field) {
        List<ExtractedEntity> result = new ArrayList<>();
        if (extractedObjects.size() == values.size()) {

            for (int i = 0; i < extractedObjects.size(); i++) {

                try {
                    ExtractedEntity extracted = extractedObjects.get(i);
                    String s = values.get(i);
                    field.setAccessible(true);
                    field.set(extracted, s);
                    result.add(extracted);
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
