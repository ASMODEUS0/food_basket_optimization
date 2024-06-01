package com.example.food_basket_optimization.configuration;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.Property;
import com.example.food_basket_optimization.extraction.properties.base.simple.JsonProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.KeyValueSimpleProperty;
import com.example.food_basket_optimization.extraction.properties.base.multi.properties.ContextualStringProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.ListedKeyValueProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.StringProperty;
import com.example.food_basket_optimization.extraction.properties.base.simple.PathProperty;
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
import com.example.food_basket_optimization.extraction.service.request.HttpProxyClient;
import com.example.food_basket_optimization.extraction.service.request.HttpProxyClientProvider;
import com.example.food_basket_optimization.extraction.service.request.executor.DefaultRequestExecutor;
import com.example.food_basket_optimization.extraction.service.request.processing.ProcessingProxyCookie;
import com.example.food_basket_optimization.extraction.service.request.processing.ProcessingRequest;
import com.example.food_basket_optimization.extraction.service.request.requesthandler.ProxyRequestHandler;
import com.example.food_basket_optimization.extraction.service.request.util.RequestUtil;
import com.example.food_basket_optimization.extractpojo.extractedentity.lenta.*;
import com.example.food_basket_optimization.selenium.page.UnknownPage;
import com.example.food_basket_optimization.selenium.page.YahooCurrentSearchPage;
import com.example.food_basket_optimization.selenium.util.DriverOptions;
import com.example.food_basket_optimization.selenium.util.Util;
import com.google.common.collect.Lists;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.message.BasicHeader;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

@Configuration
public class LentaConfiguration {

    @Bean
    public List<LentaNodeCodeExtr> lentaNodeCodes() {
        String nodeCodes = """
                gd6dd9b5e854cf23f28aa622863dd6913
                geee7643ec01603a5db2cf4819de1a033
                g6b6be260dbddd6da54dcc3ca020bf380
                g604e486481b04594c32002c67a2b459a
                g36505197bc9614e24d1020b3cfb38ee5
                g7cc5c7251a3e5503dc4122139d606465
                g523853c00788bbb520b022c130d1ae92
                g1baf1ddaa150137098383967c9a8e732
                g301007c55a37d7ff8539f1f169a4b8ae
                g68552e15008531b8ae99799a1d9391df
                g9290c81c23578165223ca2befe178b47
                g6f4a2d852409e5804606d640dc97a2b1
                gf925791ef5e5040add50a6e391cae599
                ga4638d8e16b266a51b9906c290531afb
                gd4625b4c495bddf681f01669988626db
                gad95d0db03fef392c89dff187253909a
                g1d79df330af0458391dd6307863d333e
                g81ed6bb4ec3cd75cbf9117a7e9722a1d
                g7886175ed64de08827c4fb2a9ad914f3
                g4258530b46e66c5ac62f88a56ee8bce1
                gd152557d86db1829c25705de4db3cf66
                g4477ab807af5fd53f280b1aac7816659
                gb90175be6caae9b1599e7d11326b22c3
                gce3c6ce98ad51e02445da35b93d2c7b7
                ge638b7ffc736e21c16b21710b4086220
                gb57865aeafbfc5aa8e086b86d3000a27
                g648e6f3e83892dabd3f63281dab529fd
                gaaa3a99413aa9e3963f7f07ed7a75ec0""";
        String[] nodeCodesArr = nodeCodes.split("\n");
        return Stream.of(nodeCodesArr).map(LentaNodeCodeExtr::new).toList();
    }

    @Bean
    public HttpJsonProperties lentaCityProperties(JsonMapper jsonMapper,
                                                  SourceHttpResolver sourceHttpResolver) {
        UrlBasicProperty url = new UrlBasicProperty("https", "lenta.com", "/api/v1/cities");
        HttpMethod method = HttpMethod.GET;
        StringProperty body = new StringProperty("");
        ListedKeyValueProperty params = new ListedKeyValueProperty(new ArrayList<>());
        ListedKeyValueProperty headers = new ListedKeyValueProperty(new ArrayList<>());

        ConstructableRootObjectImpl<SourceHttpProperty> constructableObject = new ConstructableRootObjectImpl<>(SourceHttpProperty.getConstructor(), List.of(url, method, body, params, headers));
        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(constructableObject);

        return new HttpJsonProperties(resolvableSource,
                LentaCityExt.class,
                jsonMapper,
                sourceHttpResolver);
    }

    @Bean
    public HttpJsonProperties lentaShopProperties(JsonMapper mapper,
                                                  SourceHttpResolver resolver,
                                                  ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext) {
        ListedConstructableNode<PathProperty> pathConstructable = new ListedConstructableNode<>(PathProperty.getConstructor(), List.of(new StringProperty("api"),
                new StringProperty("v2"),
                new StringProperty("cities"),
                new ContextualStringProperty("", extractContext, new RefValue(LentaCityExt.class, "id")),
                new StringProperty("stores")));

        ConstructableNodeImpl<? extends Property> urlConstructable = new ConstructableNodeImpl<>(UrlBasicProperty.getConstructor(), List.of(new StringProperty("https"), new StringProperty("lenta.com"), pathConstructable));

        ListedKeyValueProperty emptyProperties = new ListedKeyValueProperty(new ArrayList<>());

        ConstructableRootObjectImpl<SourceHttpProperty> rootConstrObject = new ConstructableRootObjectImpl<>(SourceHttpProperty.getConstructor(), List.of(urlConstructable, HttpMethod.GET, new StringProperty(""), emptyProperties, emptyProperties));

        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(rootConstrObject);

        return new HttpJsonProperties(resolvableSource,
                LentaStoreExt.class,
                mapper,
                resolver);
    }

    @Bean
    public HttpJsonProperties lentaProductProperties(JsonMapper mapper,
                                                     SourceHttpResolver resolver,
                                                     ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext,
                                                     ProxyRequestHandler lentaProductRequestHandler) {


        resolver.setRequestHandler(lentaProductRequestHandler);

        UrlBasicProperty url = new UrlBasicProperty("https", "lenta.com", "/api/v1/skus/list");

        HttpMethod method = HttpMethod.POST;

        ConstructableNodeImpl<KeyValueSimpleProperty> nodeCode = new ConstructableNodeImpl<>(KeyValueSimpleProperty.getConstructor(), List.of(new StringProperty("nodeCode"),
                new ContextualStringProperty("", extractContext, new RefValue(LentaNodeCodeExtr.class, "nodeCode"))));

        KeyValueSimpleProperty filters = new KeyValueSimpleProperty("filters", "[]");
        KeyValueSimpleProperty tag = new KeyValueSimpleProperty("tag", "");
        KeyValueSimpleProperty pricesRange = new KeyValueSimpleProperty("pricesRange", "null");
        KeyValueSimpleProperty typeSearch = new KeyValueSimpleProperty("typeSearch", "1");
        KeyValueSimpleProperty limit = new KeyValueSimpleProperty("limit", "24");
        KeyValueSimpleProperty offset = new KeyValueSimpleProperty("offset", "24");
        KeyValueSimpleProperty updateFilters = new KeyValueSimpleProperty("updateFilters", "true");
        KeyValueSimpleProperty sortingType = new KeyValueSimpleProperty("sortingType", "ByPopularity");

        ListedConstructableNode<JsonProperty> body = new ListedConstructableNode<>(JsonProperty.getConstructor(), List.of(nodeCode, filters, tag, pricesRange, typeSearch, limit, offset, updateFilters, sortingType));


        KeyValueSimpleProperty userAgent = new KeyValueSimpleProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.1 Safari/605.1.15");
        KeyValueSimpleProperty contentType = new KeyValueSimpleProperty("Content-Type", "application/json");
        ConstructableNodeImpl<KeyValueSimpleProperty> cookie = new ConstructableNodeImpl<>(KeyValueSimpleProperty.getConstructor(), List.of(new StringProperty("Cookie"), new ContextualStringProperty("Store=", extractContext, new RefValue(LentaStoreExt.class, "id"))));
//        KeyValueUrlContextual cookie = new KeyValueUrlContextual("Cookie", "Store=", extractContext, new RefValue(LentaStoreExt.class, "id"));


        ListedConstructableNode<ListedKeyValueProperty> headers = new ListedConstructableNode<>(ListedKeyValueProperty.getConstructor(), List.of(userAgent, contentType, cookie));

        ListedKeyValueProperty params = new ListedKeyValueProperty(List.of());

        ConstructableRootObjectImpl<SourceHttpProperty> rootObject = new ConstructableRootObjectImpl<>(SourceHttpProperty.getConstructor(), List.of(url, method, body, params, headers));

        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(rootObject);

        return new HttpJsonProperties(resolvableSource,
                LentaProductExt.class,
                mapper,
                resolver);
    }

    @Bean
    public HttpProxyClientProvider proxyClientProvider(List<HttpProxyClient> proxyClients) {
        ProcessingProxyCookie processing = (addr, port, login, pass) -> {
            String qratorName = "qrator_jsid";
            String aspNetName = "ASP.NET_SessionId";
            String customerIdName = "CustomerId";
            String validationTokenName = "ValidationToken";
            File chromeProxyExtension = Util.getProxyExtensions(addr, port.toString(), login, pass);
            ChromeOptions options = DriverOptions.defaultParseChromeDriverOptions();

            options.addExtensions(chromeProxyExtension);

            ChromeDriver browser = new ChromeDriver(options);

            YahooCurrentSearchPage yahooCurrentSearchPage = new YahooCurrentSearchPage(browser, "lenta.com");
            UnknownPage unknownPage = yahooCurrentSearchPage.clickFirstLink();

            Cookie qratorCookie = unknownPage.getCookie(qratorName);
            Cookie aspNetCookie = unknownPage.getCookie(aspNetName);
            Cookie customerIdCookie = unknownPage.getCookie(customerIdName);
            Cookie validationTokenCookie = unknownPage.getCookie(validationTokenName);
            BasicHeader cookie = new BasicHeader("Cookie", aspNetCookie.getName() + "=" + aspNetCookie.getValue());
            BasicHeader cookie1 = new BasicHeader("Cookie", qratorCookie.getName() + "=" + qratorCookie.getValue());
            BasicHeader cookie2 = new BasicHeader("Cookie", validationTokenCookie.getName() + "=" + validationTokenCookie.getValue());
            BasicHeader cookie3 = new BasicHeader("Cookie", customerIdCookie.getName() + "=" + customerIdCookie.getValue());

            browser.quit();
            return List.of(cookie3, cookie2, cookie1, cookie);
        };
        return new HttpProxyClientProvider(proxyClients, List.of(processing));
    }

    @Bean
    public ProcessingRequest lentaProductProcessingRequest() {
        return (client, request) -> {
            StringBuilder sb = new StringBuilder();
            List<Header> cookieHeaders = Lists.reverse(RequestUtil.getHeaders("Cookie", request));
            request.removeHeaders("Cookie");
            cookieHeaders.forEach(cookieHeader -> sb.append(cookieHeader.getValue()).append(";"));
            BasicHeader cookie = new BasicHeader("Cookie", sb.toString());
            request.addHeader(cookie);

            try {
                Thread.sleep(new Random().nextInt(1500, 2100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Bean
    public ProxyRequestHandler lentaProductRequestHandler(DefaultRequestExecutor executor,
                                                          HttpProxyClientProvider provider,
                                                          ProcessingRequest lentaProductProcessingRequest) {
        ProxyRequestHandler requestHandler = new ProxyRequestHandler(executor, provider);
        requestHandler.addProcessingRequest(lentaProductProcessingRequest);
        return requestHandler;
    }

}
