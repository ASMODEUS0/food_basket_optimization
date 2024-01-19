package com.example.food_basket_optimization.importer.parser.parsedobject;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@AllArgsConstructor
@Getter
public class FileJsonParsedObject implements JsonParsedObject {


    private final String path;
    private final String className;
    private final boolean isList;



    @Override
    public Class<?> getClassToParse() {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String getData() {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean isList() {
        return isList;
    }


}
