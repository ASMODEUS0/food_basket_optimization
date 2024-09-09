package com.example.food_basket_optimization.configuration;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.filtering.FilterType;
import com.example.food_basket_optimization.extraction.properties.base.postmulti.PostContextualStringProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.JsonProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.KeyValueSimpleProperty;
import com.example.food_basket_optimization.extraction.properties.base.multi.properties.ContextualStringProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.ListedKeyValueProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.StringProperty;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.JsonMapper;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.constructableobject.ConstructableNodeImpl;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.constructableobject.ConstructableRootObjectImpl;
import com.example.food_basket_optimization.extraction.properties.propertyconstructor.constructableobject.ListedConstructableNode;
import com.example.food_basket_optimization.extraction.properties.root.HttpJsonProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.ResolvableSourceHttpProperties;
import com.example.food_basket_optimization.extraction.properties.base.simple.SourceHttpProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.HttpMethod;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlBasicProperty;
import com.example.food_basket_optimization.extraction.properties.sourceresolver.SourceHttpResolver;
import com.example.food_basket_optimization.extraction.properties.util.RefValue;
import com.example.food_basket_optimization.extractpojo.extractedentity.lenta.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Profile(value = {"lenta"})
@Configuration
public class LentaConfiguration {

    private final KeyValueSimpleProperty USER_AGENT = new KeyValueSimpleProperty("User-Agent", " Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.6 Safari/605.1.15");
    private final KeyValueSimpleProperty HOST = new KeyValueSimpleProperty("Host", " lenta.com");
    private final KeyValueSimpleProperty DEVICE_ID = new KeyValueSimpleProperty("Deviceid", "d26c60c1-2ddf-1535-8892-fe3fcb88466a");
    private final KeyValueSimpleProperty TOKEN = new KeyValueSimpleProperty("SessionToken", " D08A54393E0AC4A10ED9560A21000592");
    private final KeyValueSimpleProperty RETAIL_BRAND = new KeyValueSimpleProperty("X-Retail-Brand", "lo");
    private final KeyValueSimpleProperty PLATFORM = new KeyValueSimpleProperty("X-Platform", "omniweb");
    private final KeyValueSimpleProperty OFFSET = new KeyValueSimpleProperty("offset", "0");
    private final KeyValueSimpleProperty SORT = new KeyValueSimpleProperty("sort", "{\"type\":\"popular\",\"order\":\"desc\"}");
    private final KeyValueSimpleProperty FILTERS = new KeyValueSimpleProperty("filters", "{\"range\":[],\"checkbox\":[],\"multicheckbox\":[]}");


    @Bean
    public HttpJsonProperties lentaNewShopProperties(JsonMapper jsonMapper,
                                                     SourceHttpResolver sourceHttpResolver) {

        UrlBasicProperty url = new UrlBasicProperty("https", "lenta.com", "/jrpc/pickupStoreSearch");
        HttpMethod method = HttpMethod.POST;
        StringProperty body = new StringProperty("{\"method\":\"pickupStoreSearch\",\"params\":{},\"jsonrpc\":\"2.0\",\"id\":\"pickupStoreSearch_75ba419e24b63\"}");
        ListedKeyValueProperty params = new ListedKeyValueProperty(new ArrayList<>());
        ListedKeyValueProperty headers = new ListedKeyValueProperty(List.of(USER_AGENT, TOKEN, HOST, DEVICE_ID));

        ConstructableRootObjectImpl<SourceHttpProperty> constructableObject = new ConstructableRootObjectImpl<>(SourceHttpProperty.getConstructor(), List.of(url, method, body, params, headers));

        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(constructableObject);

        return new HttpJsonProperties(resolvableSource,
                LentaCityExt.class,
                jsonMapper,
                sourceHttpResolver);
    }


    @Bean
    public HttpJsonProperties lentaCategoriesProperties(JsonMapper jsonMapper,
                                                        SourceHttpResolver sourceHttpResolver) {

        UrlBasicProperty url = new UrlBasicProperty("https", "lenta.com", "/api-gateway/v1/catalog/categories");

        HttpMethod method = HttpMethod.GET;
        StringProperty body = new StringProperty("");

        ListedKeyValueProperty params = new ListedKeyValueProperty(new ArrayList<>());
        ListedKeyValueProperty headers = new ListedKeyValueProperty(List.of(RETAIL_BRAND,
                PLATFORM,
                TOKEN,
                DEVICE_ID,
                USER_AGENT,
                HOST));

        ConstructableRootObjectImpl<SourceHttpProperty> constructableObject = new ConstructableRootObjectImpl<>(SourceHttpProperty.getConstructor(), List.of(url, method, body, params, headers));

        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(constructableObject);


        HttpJsonProperties properties = new HttpJsonProperties(resolvableSource, LentaCategoryExt.class, jsonMapper, sourceHttpResolver);
        properties.addFilterRule("parentName", FilterType.EQUAL, "");
        return properties;
    }


    @Bean
    public HttpJsonProperties lentaCatalogSizeProperties(JsonMapper jsonMapper,
                                                         SourceHttpResolver sourceHttpResolver,
                                                         ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext) {
        UrlBasicProperty url = new UrlBasicProperty("https", "lenta.com", "/api-gateway/v1/catalog/items");
        HttpMethod method = HttpMethod.POST;

        ContextualStringProperty category = new ContextualStringProperty("", extractContext, new RefValue(LentaCategoryExt.class, "id"));

        KeyValueSimpleProperty limit = new KeyValueSimpleProperty("limit", "1");


        ConstructableNodeImpl<KeyValueSimpleProperty> categoryId = new ConstructableNodeImpl<>(KeyValueSimpleProperty.getConstructor(), List.of(new StringProperty("categoryId"), category));
        ListedConstructableNode<JsonProperty> body = new ListedConstructableNode<>(JsonProperty.getConstructor(), List.of(
                categoryId,
                limit,
                OFFSET,
                SORT,
                FILTERS
        ));

        ListedKeyValueProperty params = new ListedKeyValueProperty(new ArrayList<>());
        ListedKeyValueProperty headers = new ListedKeyValueProperty(List.of(RETAIL_BRAND,
                PLATFORM,
                TOKEN,
                DEVICE_ID,
                USER_AGENT,
                HOST));

        ConstructableRootObjectImpl<SourceHttpProperty> constructableObject = new ConstructableRootObjectImpl<>(SourceHttpProperty.getConstructor(), List.of(url, method, body, params, headers));

        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(constructableObject);


        return new HttpJsonProperties(resolvableSource,
                LentaCategorySize.class,
                jsonMapper,
                sourceHttpResolver);
    }

    @Bean
    public HttpJsonProperties lentaProductProperties(JsonMapper jsonMapper,
                                                     SourceHttpResolver sourceHttpResolver,
                                                     ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext) {

        UrlBasicProperty url = new UrlBasicProperty("https", "lenta.com", "/api-gateway/v1/catalog/items");
        HttpMethod method = HttpMethod.POST;
        ContextualStringProperty category = new ContextualStringProperty("", extractContext, new RefValue(LentaCategoryExt.class, "id"));

        ConstructableNodeImpl<KeyValueSimpleProperty> limit = new ConstructableNodeImpl<>(KeyValueSimpleProperty.getConstructor(), List.of(new StringProperty("limit"),
                new PostContextualStringProperty(extractContext, new RefValue(LentaCategorySize.class, "size"))));

        ConstructableNodeImpl<KeyValueSimpleProperty> categoryId = new ConstructableNodeImpl<>(KeyValueSimpleProperty.getConstructor(), List.of(new StringProperty("categoryId"), category));

        ListedConstructableNode<JsonProperty> body = new ListedConstructableNode<>(JsonProperty.getConstructor(), List.of(
                categoryId,
                limit,
                OFFSET,
                SORT,
                FILTERS
        ));


        ListedKeyValueProperty params = new ListedKeyValueProperty(new ArrayList<>());
        ListedKeyValueProperty headers = new ListedKeyValueProperty(List.of(RETAIL_BRAND,
                PLATFORM,
                TOKEN,
                DEVICE_ID,
                USER_AGENT,
                HOST));

        ConstructableRootObjectImpl<SourceHttpProperty> constructableObject = new ConstructableRootObjectImpl<>(SourceHttpProperty.getConstructor(), List.of(url, method, body, params, headers));
        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(constructableObject);

        return new HttpJsonProperties(resolvableSource,
                LentaProductExt.class,
                jsonMapper,
                sourceHttpResolver);
    }
}
