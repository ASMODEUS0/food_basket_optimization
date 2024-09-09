package com.example.food_basket_optimization.extraction.properties.base.simple;


import com.example.food_basket_optimization.extraction.properties.SimpleProperty;
import com.example.food_basket_optimization.extraction.properties.SimplePropertyAbs;


import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;

public class JsonProperty extends SimplePropertyAbs<String> {

    private final List<KeyValueSimpleProperty> elements;


    public static Constructor<JsonProperty> getConstructor() {
        try {
            return JsonProperty.class.getConstructor(List.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

    public JsonProperty(List<KeyValueSimpleProperty> elements) {
        super(elements.stream().map(SimpleProperty::getReferenceEntities).flatMap(Collection::stream).toList());
        this.elements = elements;
    }

    @Override
    public String property() {
        return convert(elements);
    }


    private String convert(List<KeyValueSimpleProperty> elements) {
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append('{');

        elements.forEach(element -> {
            bodyBuilder.append('"').append(element.key()).append("\" ").append(':').append(element.value());
            if (elements.get(elements.size() - 1) != element) {
                bodyBuilder.append(',');
            }
        });

        bodyBuilder.append('}');

        return bodyBuilder.toString();
    }

}
