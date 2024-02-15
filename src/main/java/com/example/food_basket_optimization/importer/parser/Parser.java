package com.example.food_basket_optimization.importer.parser;

import com.example.food_basket_optimization.importer.parser.mapping.HtmlMapper;
import com.example.food_basket_optimization.importer.parser.parsedobject.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Parser {



//    public static Optional<List<Object>> parseHtml(HtmlParsedObjectContract object){
//
//
//        String htmlText = object.htmlText();
//
//
//        return HtmlMapper.readValue(htmlText, object.getClassToParse());
//    }
//
//    public static Optional<List<Object>> parseJson(JsonParsedObjectContract object){
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//        if(object.isList()){
//           return Optional.of(parseListedJson(object, objectMapper));
//        }else {
//            return Optional.of(Collections.singletonList(parseJson(object, objectMapper)));
//        }
//    }
//
//    private static List<Object> parseListedJson(JsonParsedObjectContract object, ObjectMapper mapper){
//        try {
//            TypeFactory tf = mapper.getTypeFactory();
//            JavaType type = tf.constructCollectionType(ArrayList.class, object.getClassToParse());
//            return mapper.readValue(object.getData(), type);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static Object parseJson(JsonParsedObjectContract object, ObjectMapper mapper){
//        try {
//           return mapper.readValue(object.getData(), object.getClassToParse());
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    public static Optional<List<Object>> dynamicParse(ParsedObjectContract object){
//
//        if(object instanceof HTTPHtmlParsedObject){
//            return parseHtml((HTTPHtmlParsedObject) object);
//
//        } else if (object instanceof HTTPJsonParsedObject) {
//            return parseJson((HTTPJsonParsedObject) object);
//
//        }else if (object instanceof FileJsonParsedObject){
//            return Optional.empty();
//        }
//
//       return Optional.empty();
//    }

}
