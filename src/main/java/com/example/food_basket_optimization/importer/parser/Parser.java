package com.example.food_basket_optimization.importer.parser;

import com.example.food_basket_optimization.importer.BaseResource;
import com.example.food_basket_optimization.importer.parser.parsedobject.HTTPHtmlParsedObject;
import com.example.food_basket_optimization.importer.parser.parsedobject.HtmlParsedObjectContract;
import com.example.food_basket_optimization.importer.parser.parsedobject.JsonParsedObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Parser {




    public List<Object> parseHtml(List<? extends HtmlParsedObjectContract> objects){

        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.readValue();
        ArrayList<HtmlParsedObjectContract> htmlParsedObjectContracts = new ArrayList<>();
        return objects.stream().map(this::parseHtml).toList();
    }


    public Object parseHtml(HtmlParsedObjectContract object){
        String htmlText = object.htmlText();
        Document doc = Jsoup.parse(htmlText);
        Elements select = doc.select("div.drop-list");

        return null;
    }

    public List<Object> parseJson(JsonParsedObject object){

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if(object.isList()){
           return parseListedJson(object, objectMapper);
        }else {
            return Collections.singletonList(parseJson(object, objectMapper));
        }
    }

    private List<Object> parseListedJson(JsonParsedObject object, ObjectMapper mapper){
        try {
            TypeFactory tf = mapper.getTypeFactory();
            JavaType type = tf.constructCollectionType(ArrayList.class, object.getClassToParse());
            return mapper.readValue(object.getData(), type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Object parseJson(JsonParsedObject object, ObjectMapper mapper){
        try {
           return mapper.readValue(object.getData(), object.getClassToParse());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

//
//    public Object parseObjectFromSourceFile(ParseredObject properties){
//        return null;
//    }
//
//
//
//    public  Object  initializeFrom(HTTPJsonParseObject jsonParseProperties) throws RuntimeException {
//        try {
//
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            String json = RequestService.doRequest(jsonParseProperties);
//
//            Class<?> pojoClass =  Class.forName(jsonParseProperties.getClassName());
//            JsonDeserializer deserializer = (JsonDeserializer) Class.forName(jsonParseProperties.getDeserializationClassName())
//                    .getDeclaredConstructor(null)
//                    .newInstance(null);
//
//            SimpleModule module = new SimpleModule();
//            module.addDeserializer(pojoClass, deserializer);
//
//            objectMapper.registerModule(module);
//
//            Object result = objectMapper.readValue(json, pojoClass);
//
//            return result;
//        } catch (ClassNotFoundException | JsonProcessingException | InvocationTargetException | InstantiationException |
//                 IllegalAccessException | NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
