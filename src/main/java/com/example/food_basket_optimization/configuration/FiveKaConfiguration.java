package com.example.food_basket_optimization.configuration;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.base.multi.properties.ContextualStringProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.*;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.JsonMapper;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.constructableobject.ConstructableNodeImpl;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.constructableobject.ConstructableRootObjectImpl;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.constructableobject.ListedConstructableNode;
import com.example.food_basket_optimization.extraction.properties.root.HttpJsonProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.ResolvableSourceHttpProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlBasicProperty;
import com.example.food_basket_optimization.extraction.properties.sourceresolver.SourceHttpResolver;
import com.example.food_basket_optimization.extraction.properties.util.RefValue;
import com.example.food_basket_optimization.extractpojo.extractedentity.fiveka.FiveKaCategoryExt;
import com.example.food_basket_optimization.extractpojo.extractedentity.fiveka.FiveKaProductExt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.concurrent.ConcurrentMap;


@Profile(value = "5ka")
@Configuration
public class FiveKaConfiguration {


    @Bean
    public HttpJsonProperties fiveKaCategoriesProperty(JsonMapper mapper,
                                                       SourceHttpResolver resolver) {
        UrlBasicProperty url = new UrlBasicProperty("https", "5d.5ka.ru", "/api/catalog/v1/stores/334E/categories?mode=delivery&include_subcategories=1");
        ListedKeyValueProperty params = new ListedKeyValueProperty(List.of());
        ListedKeyValueProperty headers = new ListedKeyValueProperty(List.of());

        ConstructableRootObjectImpl<SourceHttpProperty> constructableObject = new ConstructableRootObjectImpl<>(SourceHttpProperty.getConstructor(),
                List.of(url, HttpMethod.GET, new StringProperty(""), params, headers));

        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(constructableObject);
        return new HttpJsonProperties(resolvableSource, FiveKaCategoryExt.class, mapper, resolver);
    }

    @Bean
    public HttpJsonProperties fiveKaProductsProperty(JsonMapper mapper,
                                                     SourceHttpResolver resolver,
                                                     ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext) {

        ListedConstructableNode<StringProperty> listedPath = new ListedConstructableNode<>(StringProperty.getListedConstructor(), List.of(new StringProperty("/api/catalog/v1/stores/300E/categories/"),
                new ContextualStringProperty("", extractContext, new RefValue(FiveKaCategoryExt.class, "id")), new StringProperty("/products?mode=delivery&limit=499")));

        ConstructableNodeImpl<UrlBasicProperty> urlConstructable = new ConstructableNodeImpl<>(UrlBasicProperty.getConstructor(), List.of(new StringProperty("https"),
                new StringProperty("5d.5ka.ru"), listedPath));
        StringProperty body = new StringProperty("");
        ListedKeyValueProperty params = new ListedKeyValueProperty(List.of());
        ListedKeyValueProperty headers = new ListedKeyValueProperty(List.of());

        ConstructableRootObjectImpl<SourceHttpProperty> constructableObject = new ConstructableRootObjectImpl<>(SourceHttpProperty.getConstructor(),
                List.of(urlConstructable, HttpMethod.GET, body, params, headers));

        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(constructableObject);
        return new HttpJsonProperties(resolvableSource, FiveKaProductExt.class, mapper, resolver);
    }

}
