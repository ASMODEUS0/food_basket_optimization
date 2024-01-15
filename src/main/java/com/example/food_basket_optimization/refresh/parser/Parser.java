package com.example.food_basket_optimization.refresh.parser;

import com.example.food_basket_optimization.refresh.deserialization.DeserializationObject;
import com.example.food_basket_optimization.refresh.parser.model.Parsered;
import com.example.food_basket_optimization.refresh.parser.properties.FileParseProperties;
import com.example.food_basket_optimization.refresh.parser.properties.htpp.JsonParseProperties;
import com.example.food_basket_optimization.refresh.parser.service.RequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Parser {


    private final  ObjectMapper om;


    public  <P extends Parsered> List<P> parse(){


        return null;
    }

    public <P> List<P> initializeFromSourceFile(FileParseProperties properties){

        try {
            File sourceFile = new File(properties.getPath());

            Class<?> clazz = Class.forName(properties.getClassName());
//            ObjectMapper om = new ObjectMapper();
//            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            TypeFactory typeFactory = om.getTypeFactory();

            return om.<ArrayList<P>>readValue(sourceFile, typeFactory.constructCollectionType(List.class, clazz));
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }



    public  Object  initializeFrom(JsonParseProperties jsonParseProperties) throws RuntimeException {
        try {

            ObjectMapper objectMapper = new ObjectMapper();

            String json = RequestService.doRequest(jsonParseProperties);

            Class<?> pojoClass =  Class.forName(jsonParseProperties.getClassName());
            JsonDeserializer deserializer = (JsonDeserializer) Class.forName(jsonParseProperties.getDeserializationClassName())
                    .getDeclaredConstructor(null)
                    .newInstance(null);

            SimpleModule module = new SimpleModule();
            module.addDeserializer(pojoClass, deserializer);

            objectMapper.registerModule(module);

            Object result = objectMapper.readValue(json, pojoClass);

            return result;
        } catch (ClassNotFoundException | JsonProcessingException | InvocationTargetException | InstantiationException |
                 IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
