package com.example.food_basket_optimization.configuration;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.base.multi.ContextualStringProperty;
import com.example.food_basket_optimization.extraction.properties.base.multi.MultiString;
import com.example.food_basket_optimization.extraction.properties.base.multi.MultiStringProperty;
import com.example.food_basket_optimization.extraction.properties.body.JsonMultiBodyProperties;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.JsonMapper;
import com.example.food_basket_optimization.extraction.properties.root.HttpJsonProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.ResolvableSourceHttpProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.*;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.SimpleMultiUrl;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlMultiPath;
import com.example.food_basket_optimization.extraction.properties.sourceresolver.SourceHttpResolver;
import com.example.food_basket_optimization.extraction.properties.util.RefValue;
import com.example.food_basket_optimization.extraction.service.request.*;
import com.example.food_basket_optimization.extraction.service.request.executor.DefaultRequestExecutor;
import com.example.food_basket_optimization.extraction.service.request.processing.ProcessingProxyCookie;
import com.example.food_basket_optimization.extraction.service.request.processing.ProcessingRequest;
import com.example.food_basket_optimization.extraction.service.request.requesthandler.ProxyRequestHandler;
import com.example.food_basket_optimization.extraction.service.request.util.RequestUtil;
import com.example.food_basket_optimization.extractpojo.extractedentity.lenta.LentaCityExt;
import com.example.food_basket_optimization.extractpojo.extractedentity.lenta.LentaProductExt;
import com.example.food_basket_optimization.extractpojo.extractedentity.lenta.LentaStoreExt;
import com.example.food_basket_optimization.selenium.page.UnknownPage;
import com.example.food_basket_optimization.selenium.util.DriverOptions;
import com.example.food_basket_optimization.selenium.util.Util;
import com.example.food_basket_optimization.selenium.page.YahooCurrentSearchPage;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;

@Configuration
public class LentaConfiguration {

    @Bean
    public HttpJsonProperties lentaCityProperties(JsonMapper jsonMapper,
                                                  SourceHttpResolver sourceHttpResolver) {
        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(new KeyValueUrlMultiUnion(new ArrayList<>()),
                new KeyValueUrlMultiUnion(new ArrayList<>()),
                HttpMethod.GET,
                new SimpleMultiUrl("https", "lenta.com", "/api/v1/cities"),
                new MultiStringProperty(""));

        return new HttpJsonProperties(resolvableSource,
                LentaCityExt.class,
                jsonMapper,
                sourceHttpResolver);
    }


    @Bean
    public HttpJsonProperties lentaShopProperties(JsonMapper mapper,
                                                  SourceHttpResolver resolver,
                                                  ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext) {

        UrlMultiPath multiUrl = new UrlMultiPath("https", "lenta.com", List.of(new MultiStringProperty("api"),
                new MultiStringProperty("v2"),
                new MultiStringProperty("cities"),
                new ContextualStringProperty("", extractContext, new RefValue(LentaCityExt.class, "id")),
                new MultiStringProperty("stores")));

        KeyValueUrlMultiUnionProperties emptyProperties = new KeyValueUrlMultiUnion(new ArrayList<>());

        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(emptyProperties,
                emptyProperties,
                HttpMethod.GET,
                multiUrl,
                new MultiStringProperty(""));


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

        KeyValueUrlMultiUnionProperties emptyProperties = new KeyValueUrlMultiUnion(new ArrayList<>());

        KeyValueUrlMultiUnion headers = new KeyValueUrlMultiUnion(List.of(
                new KeyValueUrlContextual("Cookie", "Store=", extractContext, new RefValue(LentaStoreExt.class, "id")),
                new SimpleKeyValueUrlMulti("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.1 Safari/605.1.15"),
                new SimpleKeyValueUrlMulti("Content-Type", "application/json")));


        LinkedHashMap<String, MultiString> jsonElements = new LinkedHashMap<>();
        jsonElements.put("nodeCode", new MultiStringProperty("geee7643ec01603a5db2cf4819de1a033"));
        jsonElements.put("filters", new MultiStringProperty("[]"));
        jsonElements.put("tag", new MultiStringProperty(""));
        jsonElements.put("pricesRange", new MultiStringProperty("null"));
        jsonElements.put("typeSearch", new MultiStringProperty("1"));
        jsonElements.put("limit", new MultiStringProperty("24"));
        jsonElements.put("offset", new MultiStringProperty("96"));
        jsonElements.put("updateFilters", new MultiStringProperty("true"));
        jsonElements.put("sortingType", new MultiStringProperty("ByPopularity"));

        JsonMultiBodyProperties body = new JsonMultiBodyProperties(jsonElements);

//        MultiStringProperty body = new MultiStringProperty("{\"nodeCode\":\"g604e486481b04594c32002c67a2b459a\",\"filters\":[],\"tag\":\"\",\"pricesRange\":null,\"typeSearch\":1,\"limit\":24,\"updateFilters\":true,\"offset\":96,\"sortingType\":\"ByPopularity\"}");


        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(emptyProperties,
                headers,
                HttpMethod.POST,
                new SimpleMultiUrl("https", "lenta.com", "/api/v1/skus/list"),
                body
        );


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
