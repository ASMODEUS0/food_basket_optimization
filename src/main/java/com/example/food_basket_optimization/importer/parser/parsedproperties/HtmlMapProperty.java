package com.example.food_basket_optimization.importer.parser.parsedproperties;


import java.util.List;
import java.util.Map;

public class HtmlMapProperty implements MapProperty   {

    private final String htmlStr;
    private final Class<?> classToParse;
    private final Map<String, Object> referenceEntities;

    public HtmlMapProperty(String htmlStr, Class<?> classToParse, Map<String, Object> referenceEntities) {
        this.htmlStr = htmlStr;
        this.classToParse = classToParse;
        this.referenceEntities = referenceEntities;
    }

    @Override
    public String getData() {
        return htmlStr;
    }

    @Override
    public Class<?> getClassToParse() {
        return classToParse;
    }

    @Override
    public Map<String ,Object> getReferenceEntities() {
        return referenceEntities;
    }
}
