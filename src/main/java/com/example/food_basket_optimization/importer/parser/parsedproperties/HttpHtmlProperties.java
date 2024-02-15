package com.example.food_basket_optimization.importer.parser.parsedproperties;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * Represent object to get controller parsing over HTTP, information will be received as Html
 */
public class HttpHtmlProperties  implements ParsedProperties<SourceHttp> {

    private final SourceHttp source;
    private final Class<?> classToParse;
    private final HtmlMapper mapper;
    private final HttpSourceResolver sourceResolver = new HttpSourceResolver();
    private final ConcurrentMap<Class<?>, List<?>> context;

    public HttpHtmlProperties(SourceHttp source,
                              Class<?> classToParse,
                              ConcurrentMap<Class<?>, List<?>> context,
                              HtmlMapper mapper) {
        this.source = source;
        this.classToParse = classToParse;
        this.context = context;
        this.mapper = mapper;

    }
    @Override
    public SourceHttp getParsedSource() {
        return source;
    }

    @Override
    public Class<?> getClassToParse() {
        return classToParse;
    }

    @Override
    public Mapper getMapper() {
        return mapper;
    }

    @Override
    public HttpSourceResolver getSourceResolver() {
        return sourceResolver;
    }

    //todo: Think
    @Override
    public Boolean parseIsPossible() {
      for(Class<?> ref: source.getReferences()){
          if(!context.containsKey(ref)){
              return false;
          }
      }
      return true;
    }

}
