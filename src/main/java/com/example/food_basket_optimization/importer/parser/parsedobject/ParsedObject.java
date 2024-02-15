package com.example.food_basket_optimization.importer.parser.parsedobject;


import com.example.food_basket_optimization.importer.Importer;
import com.example.food_basket_optimization.importer.parser.parsedproperties.*;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Optional;

public class ParsedObject <T extends ParsedSourceContract>  implements ParsedObjectContract {


    private final Importer importer;
    private final ParsedProperties<T> properties;


    public  ParsedObject(Importer importer, ParsedProperties<T> properties) {
        this.importer = importer;
        this.properties = properties;
    }

    @Override
    public List<Object> parse() {

        T parsedSource = properties.getParsedSource();

        SourceResolverContract<T> sourceResolver = properties.getSourceResolver();
        List<? extends MapProperty> data = sourceResolver.getData(parsedSource, properties.getClassToParse());
        Mapper mapper = properties.getMapper();

       List<Object> result = mapper.map(data);
       importer.getImportContext().put(properties.getClassToParse(), result);
        importer.getSupport().firePropertyChange(new PropertyChangeEvent(this, null, null, null));
        return result;
    }

    @Override
    public Boolean parseIsPossible(){
        return properties.parseIsPossible();
    }


}
