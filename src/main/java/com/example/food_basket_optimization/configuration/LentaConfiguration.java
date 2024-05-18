package com.example.food_basket_optimization.configuration;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.base.multi.*;
import com.example.food_basket_optimization.extraction.properties.base.simple.SimpleString;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.JsonMapper;
import com.example.food_basket_optimization.extraction.properties.root.HttpJsonProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.ResolvableSourceHttpProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.*;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.SimpleMultiUrl;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlMultiPath;
import com.example.food_basket_optimization.extraction.properties.sourceresolver.SourceHttpResolver;
import com.example.food_basket_optimization.extraction.properties.util.MultiplyingProperty;
import com.example.food_basket_optimization.extraction.properties.util.RefValue;
import com.example.food_basket_optimization.extraction.service.request.*;
import com.example.food_basket_optimization.extraction.service.request.executor.DefaultRequestExecutor;
import com.example.food_basket_optimization.extraction.service.request.processing.ProcessingProxyCookie;
import com.example.food_basket_optimization.extraction.service.request.processing.ProcessingRequest;
import com.example.food_basket_optimization.extraction.service.request.requesthandler.ProxyRequestHandler;
import com.example.food_basket_optimization.extraction.service.request.util.RequestUtil;
import com.example.food_basket_optimization.extractpojo.extractedentity.lenta.*;
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
import java.util.stream.Stream;

@Configuration
public class LentaConfiguration {

//    @Bean
//   public List<LentaNodeCodeExtr> lentaNodeCodes() {
//        String nodeCodes = "gd6dd9b5e854cf23f28aa622863dd6913\n" +
//                           "g0a4c6ef96090b5b3db5f6aa0f2c20563\n";
////                           "geee7643ec01603a5db2cf4819de1a033\n" +
////                           "g6b6be260dbddd6da54dcc3ca020bf380\n" +
////                           "g604e486481b04594c32002c67a2b459a\n" +
////                           "g36505197bc9614e24d1020b3cfb38ee5\n" +
////                           "g7cc5c7251a3e5503dc4122139d606465\n" +
////                           "g523853c00788bbb520b022c130d1ae92\n" +
////                           "g1baf1ddaa150137098383967c9a8e732\n" +
////                           "g301007c55a37d7ff8539f1f169a4b8ae\n" +
////                           "g68552e15008531b8ae99799a1d9391df\n" +
////                           "g9290c81c23578165223ca2befe178b47\n" +
////                           "g6f4a2d852409e5804606d640dc97a2b1\n" +
////                           "gf925791ef5e5040add50a6e391cae599\n" +
////                           "ga4638d8e16b266a51b9906c290531afb\n" +
////                           "gd4625b4c495bddf681f01669988626db\n" +
////                           "gad95d0db03fef392c89dff187253909a\n" +
////                           "g1d79df330af0458391dd6307863d333e\n" +
////                           "g81ed6bb4ec3cd75cbf9117a7e9722a1d\n" +
////                           "g7886175ed64de08827c4fb2a9ad914f3\n" +
////                           "g4258530b46e66c5ac62f88a56ee8bce1\n" +
////                           "gd152557d86db1829c25705de4db3cf66\n" +
////                           "g4477ab807af5fd53f280b1aac7816659\n" +
////                           "gb90175be6caae9b1599e7d11326b22c3\n" +
////                           "gce3c6ce98ad51e02445da35b93d2c7b7\n" +
////                           "ge638b7ffc736e21c16b21710b4086220\n" +
////                           "gb57865aeafbfc5aa8e086b86d3000a27\n" +
////                           "g648e6f3e83892dabd3f63281dab529fd\n" +
////                           "gaaa3a99413aa9e3963f7f07ed7a75ec0";
//        String[] nodeCodesArr = nodeCodes.split("\n");
//        return Stream.of(nodeCodesArr).map(LentaNodeCodeExtr::new).toList();
//    }
//
//    @Bean
//    public HttpJsonProperties lentaCatalogSizeProperties(JsonMapper jsonMapper,
//                                                         SourceHttpResolver resolver,
//                                                         ProxyRequestHandler lentaProductRequestHandler,
//                                                         ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext) {
//        resolver.setRequestHandler(lentaProductRequestHandler);
//        KeyValueUrlMultiUnion headers = new KeyValueUrlMultiUnion(List.of(
//                new KeyValueUrlContextual("Cookie", "Store=", extractContext, new RefValue(LentaStoreExt.class, "id")),
//                new SimpleKeyValueUrlMulti("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.1 Safari/605.1.15"),
//                new SimpleKeyValueUrlMulti("Content-Type", "application/json")));
//
//
//        LinkedHashMap<String, MultiplyingProperty<SimpleString>> jsonElements = new LinkedHashMap<>();
//        jsonElements.put("nodeCode", new ContextualStringProperty("", extractContext, new RefValue(LentaNodeCodeExtr.class, "nodeCode")));
//        jsonElements.put("filters", new MultiStringProperty("[]"));
//        jsonElements.put("tag", new MultiStringProperty(""));
//        jsonElements.put("pricesRange", new MultiStringProperty("null"));
//        jsonElements.put("typeSearch", new MultiStringProperty("1"));
//        jsonElements.put("limit", new MultiStringProperty("24"));
//        jsonElements.put("offset", new MultiStringProperty("24"));
//        jsonElements.put("updateFilters", new MultiStringProperty("true"));
//        jsonElements.put("sortingType", new MultiStringProperty("ByPopularity"));
//
//        JsonMultiBodyProperties body = new JsonMultiBodyProperties(jsonElements);
//
//        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(List.of(new KeyValueUrlMultiUnion(new ArrayList<>()),
//                headers,
//                new SimpleMultiProperty<>(HttpMethod.POST),
//                new SimpleMultiUrl("https", "lenta.com", "/api/v1/skus/list"),
//                body), List.of());
//
//        return new HttpJsonProperties(resolvableSource,
//                LentaCatalogSizeExtr.class,
//                jsonMapper,
//                resolver);
//    }
//
//    @Bean
//    public HttpJsonProperties lentaCityProperties(JsonMapper jsonMapper,
//                                                  SourceHttpResolver sourceHttpResolver) {
//        KeyValueUrlMultiUnion headers = new KeyValueUrlMultiUnion(new ArrayList<>());
//        KeyValueUrlMultiUnion params = new KeyValueUrlMultiUnion(new ArrayList<>());
//        SimpleMultiProperty<HttpMethod> method = new SimpleMultiProperty<>(HttpMethod.GET);
//        SimpleMultiUrl url = new SimpleMultiUrl("https", "lenta.com", "/api/v1/cities");
//        MultiStringProperty body = new MultiStringProperty("");
//
//        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(List.of(headers,
//                params,
//                method,
//                url,
//                body), List.of());
//
//
//        return new HttpJsonProperties(resolvableSource,
//                LentaCityExt.class,
//                jsonMapper,
//                sourceHttpResolver);
//    }
//
//
//    @Bean
//    public HttpJsonProperties lentaShopProperties(JsonMapper mapper,
//                                                  SourceHttpResolver resolver,
//                                                  ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext) {
//
//        UrlMultiPath multiUrl = new UrlMultiPath("https", "lenta.com", List.of(new MultiStringProperty("api"),
//                new MultiStringProperty("v2"),
//                new MultiStringProperty("cities"),
//                new ContextualStringProperty("", extractContext, new RefValue(LentaCityExt.class, "id")),
//                new MultiStringProperty("stores")));
//
//        KeyValueUrlMultiUnionProperties emptyProperties = new KeyValueUrlMultiUnion(new ArrayList<>());
//        SimpleMultiProperty<HttpMethod> method = new SimpleMultiProperty<>(HttpMethod.GET);
//        MultiStringProperty body = new MultiStringProperty("");
//
//        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(List.of(emptyProperties,
//                emptyProperties,
//                method,
//                multiUrl,
//                body), List.of());
//
//
//        return new HttpJsonProperties(resolvableSource,
//                LentaStoreExt.class,
//                mapper,
//                resolver);
//    }
//
//
//    @Bean
//    public HttpJsonProperties lentaProductProperties(JsonMapper mapper,
//                                                     SourceHttpResolver resolver,
//                                                     ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext,
//                                                     ProxyRequestHandler lentaProductRequestHandler) {
//
//        resolver.setRequestHandler(lentaProductRequestHandler);
//
//        KeyValueUrlMultiUnionProperties emptyProperties = new KeyValueUrlMultiUnion(new ArrayList<>());
//
//        KeyValueUrlMultiUnion headers = new KeyValueUrlMultiUnion(List.of(
//                new KeyValueUrlContextual("Cookie", "Store=", extractContext, new RefValue(LentaStoreExt.class, "id")),
//                new SimpleKeyValueUrlMulti("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.1 Safari/605.1.15"),
//                new SimpleKeyValueUrlMulti("Content-Type", "application/json")));
//
//
//        LinkedHashMap<String, MultiplyingProperty<SimpleString>> jsonElements = new LinkedHashMap<>();
//        jsonElements.put("nodeCode", new ContextualStringProperty("", extractContext, new RefValue(LentaNodeCodeExtr.class, "nodeCode")));
//        jsonElements.put("filters", new MultiStringProperty("[]"));
//        jsonElements.put("tag", new MultiStringProperty(""));
//        jsonElements.put("pricesRange", new MultiStringProperty("null"));
//        jsonElements.put("typeSearch", new MultiStringProperty("1"));
//        jsonElements.put("limit", new MultiStringProperty("24"));
//        jsonElements.put("offset", new MultiStringProperty("24"));
//        jsonElements.put("updateFilters", new MultiStringProperty("true"));
//        jsonElements.put("sortingType", new MultiStringProperty("ByPopularity"));
//
//
////        jsonElements.put("offset", new IterableContextualPostMulti(new CatalogStoreRefValueResolver(),
////                "",
////                24,
////                (n)-> n+24,
////                extractContext));
//
//        SimpleMultiProperty<HttpMethod> method = new SimpleMultiProperty<>(HttpMethod.POST);
//        JsonMultiBodyProperties body = new JsonMultiBodyProperties(jsonElements);
//
//
//        SimpleMultiUrl url = new SimpleMultiUrl("https", "lenta.com", "/api/v1/skus/list");
//        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(List.of(emptyProperties,
//                headers,
//                method,
//                url,
//                body), List.of()
//        );
//
//
//        return new HttpJsonProperties(resolvableSource,
//                LentaProductExt.class,
//                mapper,
//                resolver);
//    }
//
//    @Bean
//    public HttpProxyClientProvider proxyClientProvider(List<HttpProxyClient> proxyClients) {
//        ProcessingProxyCookie processing = (addr, port, login, pass) -> {
//            String qratorName = "qrator_jsid";
//            String aspNetName = "ASP.NET_SessionId";
//            String customerIdName = "CustomerId";
//            String validationTokenName = "ValidationToken";
//            File chromeProxyExtension = Util.getProxyExtensions(addr, port.toString(), login, pass);
//            ChromeOptions options = DriverOptions.defaultParseChromeDriverOptions();
//
//            options.addExtensions(chromeProxyExtension);
//
//            ChromeDriver browser = new ChromeDriver(options);
//
//            YahooCurrentSearchPage yahooCurrentSearchPage = new YahooCurrentSearchPage(browser, "lenta.com");
//            UnknownPage unknownPage = yahooCurrentSearchPage.clickFirstLink();
//
//            Cookie qratorCookie = unknownPage.getCookie(qratorName);
//            Cookie aspNetCookie = unknownPage.getCookie(aspNetName);
//            Cookie customerIdCookie = unknownPage.getCookie(customerIdName);
//            Cookie validationTokenCookie = unknownPage.getCookie(validationTokenName);
//            BasicHeader cookie = new BasicHeader("Cookie", aspNetCookie.getName() + "=" + aspNetCookie.getValue());
//            BasicHeader cookie1 = new BasicHeader("Cookie", qratorCookie.getName() + "=" + qratorCookie.getValue());
//            BasicHeader cookie2 = new BasicHeader("Cookie", validationTokenCookie.getName() + "=" + validationTokenCookie.getValue());
//            BasicHeader cookie3 = new BasicHeader("Cookie", customerIdCookie.getName() + "=" + customerIdCookie.getValue());
//
//            browser.quit();
//            return List.of(cookie3, cookie2, cookie1, cookie);
//        };
//        return new HttpProxyClientProvider(proxyClients, List.of(processing));
//    }
//
//
//    @Bean
//    public ProcessingRequest lentaProductProcessingRequest() {
//        return (client, request) -> {
//            StringBuilder sb = new StringBuilder();
//            List<Header> cookieHeaders = Lists.reverse(RequestUtil.getHeaders("Cookie", request));
//            request.removeHeaders("Cookie");
//            cookieHeaders.forEach(cookieHeader -> sb.append(cookieHeader.getValue()).append(";"));
//            BasicHeader cookie = new BasicHeader("Cookie", sb.toString());
//            request.addHeader(cookie);
//
//            try {
//                Thread.sleep(new Random().nextInt(1500, 2100));
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        };
//    }
//
//
//    @Bean
//    public ProxyRequestHandler lentaProductRequestHandler(DefaultRequestExecutor executor,
//                                                          HttpProxyClientProvider provider,
//                                                          ProcessingRequest lentaProductProcessingRequest) {
//        ProxyRequestHandler requestHandler = new ProxyRequestHandler(executor, provider);
//        requestHandler.addProcessingRequest(lentaProductProcessingRequest);
//        return requestHandler;
//    }


}
