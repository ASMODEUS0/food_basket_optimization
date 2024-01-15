package com.example.food_basket_optimization.refresh;

import com.example.food_basket_optimization.refresh.parser.Parser;
import com.example.food_basket_optimization.refresh.parser.properties.FileParseProperties;
import com.example.food_basket_optimization.refresh.parser.properties.htpp.HTTPParseProperties;
import com.example.food_basket_optimization.refresh.parser.properties.htpp.JsonParseProperties;
import com.example.food_basket_optimization.refresh.properties.RefreshProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
public class Refresh {
    @Autowired
    public  Parser parser;
    @Autowired
    public  RefreshProperties refreshProperties;



    //Updates data from the base settings file
    public  void  refresh(ApplicationContext context){

//        RefreshProperties refreshproperties = context.getBean("refreshProperties", RefreshProperties.class);
//        FileParseProperties fileParseProperties = refreshproperties.getFileParseProperties().get(0);
//        List<Object> objects = parser.initializeFromSourceFile(fileParseProperties);

        startRefreshConfiguration();



    }

    private boolean startRefreshConfiguration(){
//        List<List<Object>> list = refreshProperties.getFileParseProperties().stream().map(parser::initializeFromSourceFile).toList();

        List<Object> list1 = refreshProperties.getJsonsParseProperties().stream().map(parser::initializeFrom).toList();


        return true;
    }

    public void refreshWithOptions(List<HTTPParseProperties> properties){

    }
}
