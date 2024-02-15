package com.example.food_basket_optimization.importer.parser.parsedproperties.url;

import com.example.food_basket_optimization.pojo.DiksiCity;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 */
public class KeyValueUrlContextual implements KeyValueUrlProperty {

    private final String value;
    private final String key;
    private final ConcurrentMap<Class<?>, List<?>> context;
    @Getter
    private final Class<?> refClass;

    private final String REF_PATTERN = "REF\\([A-Za-z0-9+_.-]+[|][A-Za-z0-9+_.-]+\\)$";

    public KeyValueUrlContextual(String value, String key, ConcurrentMap<Class<?>, List<?>> context, Class<?> refClass) {
        this.value = value;
        this.key = key;
        this.context = context;
        this.refClass = refClass;
    }



    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public List<KeyValueUrlBasicContextual> multiply() {
        ArrayList<KeyValueUrlBasicContextual> result = new ArrayList<>();


        Pattern pattern = Pattern.compile(REF_PATTERN);
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            String group = matcher.group();
            Pattern haslPatterb = Pattern.compile("[A-Za-z0-9+_.-]+[|][A-Za-z0-9+_.-]+");
            Matcher matcher1 = haslPatterb.matcher(group);
            matcher1.find();

            String group1 = matcher1.group();
            String[] split = group1.split("[|]");

            if (split.length == 2) {
                String className = split[0];
                String propertyName = split[1];


            try {
                Class<?> aClass = Class.forName(className);
                Map<Object, Object> contextualReferencesValues = getContextualReferencesValues(aClass, propertyName);

                contextualReferencesValues.forEach((object, fieldValue)-> {
                    String fieldValueStr = matcher.replaceAll(String.valueOf(fieldValue));
                    KeyValueUrlBasicContextual keyValueUrlBasicContextual = new KeyValueUrlBasicContextual(key, fieldValueStr, object);
                    result.add(keyValueUrlBasicContextual);
                });



            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("Can't find class " + className);
            }
            }
        }
        return result;


    }

    //todo: Find another solution
    private Map<Object, Object> getContextualReferencesValues(Class<?> clazz, String fieldName) {
        if (!context.containsKey(clazz)) {
            throw new IllegalStateException("The context does not match the specified properties, class with name: " + clazz.getName() + "missing in context");
        }

        List<?> objects = context.get(clazz);

        Map<Object, Object> valueField = new HashMap<>();
        for(Object object: objects){
            try {
                Field declaredField = object.getClass().getDeclaredField(fieldName);
                declaredField.setAccessible(true);
                Object field = declaredField.get(object);
                valueField.put(object, field);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return valueField;
    }


}
