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
import com.example.food_basket_optimization.extractpojo.extractedentity.magnit.MagnitCityExt;
import com.example.food_basket_optimization.extractpojo.extractedentity.magnit.MagnitProductExt;
import com.example.food_basket_optimization.extractpojo.extractedentity.magnit.MagnitStoreExt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Configuration
public class MagnitConfiguration {

//    @Bean
//    public HttpJsonProperties magnitCity(SourceHttpResolver sourceResolver,
//                                         JsonMapper mapper
//    ) {
//
//        UrlBasicProperty url = new UrlBasicProperty("https", "web-gateway.middle-api.magnit.ru", "/v1/cities");
//
//        HttpMethod method = HttpMethod.GET;
//
//        StringProperty body = new StringProperty("");
//
//        KeyValueSimpleProperty host = new KeyValueSimpleProperty("Host", " web-gateway.middle-api.magnit.ru");
//        KeyValueSimpleProperty userAgent = new KeyValueSimpleProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36");
//        KeyValueSimpleProperty appVersion = new KeyValueSimpleProperty("x-app-version", " 0.1.0");
//        KeyValueSimpleProperty clientName = new KeyValueSimpleProperty("x-client-name", " magnit");
//        KeyValueSimpleProperty deviceId = new KeyValueSimpleProperty("x-device-id", "dgvm5fesvl");
//        KeyValueSimpleProperty deviceTag = new KeyValueSimpleProperty("x-device-tag", "disabled");
//        KeyValueSimpleProperty platformVersion = new KeyValueSimpleProperty("x-platform-version", " window.navigator.userAgent");
//
//
//        ListedConstructableNode<ListedKeyValueProperty> headers = new ListedConstructableNode<>(ListedKeyValueProperty.getConstructor(), List.of(host,
//                userAgent,
//                appVersion,
//                clientName,
//                deviceId,
//                deviceTag,
//                platformVersion));
//
//        ListedKeyValueProperty params = new ListedKeyValueProperty(List.of());
//
//
//        ConstructableRootObjectImpl<SourceHttpProperty> root = new ConstructableRootObjectImpl<>(SourceHttpProperty.getConstructor(), List.of(url,
//                method,
//                body,
//                params,
//                headers));
//
//        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(root);
//
//        return new HttpJsonProperties(resolvableSource,
//                MagnitCityExt.class,
//                mapper,
//                sourceResolver);
//
//    }
//
//    @Bean
//    public HttpJsonProperties magnitShops(SourceHttpResolver sourceResolver,
//                                          JsonMapper mapper,
//                                          ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractedContext) {
//// https://web-gateway.middle-api.magnit.ru/v1/geolocation/store?
//
//
//        UrlBasicProperty url = new UrlBasicProperty("https", "web-gateway.middle-api.magnit.ru", "/v1/geolocation/store");
//
//        HttpMethod method = HttpMethod.GET;
//
//        StringProperty body = new StringProperty("");
//
//        KeyValueSimpleProperty host = new KeyValueSimpleProperty("Host", " web-gateway.middle-api.magnit.ru");
//        KeyValueSimpleProperty userAgent = new KeyValueSimpleProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36");
//        KeyValueSimpleProperty appVersion = new KeyValueSimpleProperty("x-app-version", " 0.1.0");
//        KeyValueSimpleProperty clientName = new KeyValueSimpleProperty("x-client-name", " magnit");
//        KeyValueSimpleProperty deviceId = new KeyValueSimpleProperty("x-device-id", "dgvm5fesvl");
//        KeyValueSimpleProperty deviceTag = new KeyValueSimpleProperty("x-device-tag", "disabled");
//        KeyValueSimpleProperty platformVersion = new KeyValueSimpleProperty("x-platform-version", " window.navigator.userAgent");
//
//
//        ListedConstructableNode<ListedKeyValueProperty> headers = new ListedConstructableNode<>(ListedKeyValueProperty.getConstructor(), List.of(host,
//                userAgent,
//                appVersion,
//                clientName,
//                deviceId,
//                deviceTag,
//                platformVersion));
//
//
//        ContextualStringProperty longitudeString = new ContextualStringProperty("", extractedContext, new RefValue(MagnitCityExt.class, "longitude"));
//        ConstructableNodeImpl<KeyValueSimpleProperty> longitude = new ConstructableNodeImpl<>(KeyValueSimpleProperty.getConstructor(), List.of(new StringProperty("Longitude"), longitudeString));
//
//        ContextualStringProperty latitudeString = new ContextualStringProperty("", extractedContext, new RefValue(MagnitCityExt.class, "latitude"));
//        ConstructableNodeImpl<KeyValueSimpleProperty> latitude = new ConstructableNodeImpl<>(KeyValueSimpleProperty.getConstructor(), List.of(new StringProperty("Latitude"), latitudeString));
////        KeyValueSimpleProperty longitude = new KeyValueSimpleProperty("Longitude", "37.617645");
////        KeyValueSimpleProperty latitude = new KeyValueSimpleProperty("Latitude", "55.755817");
//        KeyValueSimpleProperty radius = new KeyValueSimpleProperty("Radius", "40");
//        KeyValueSimpleProperty limit = new KeyValueSimpleProperty("Limit", "1000");
//
//
//        ListedConstructableNode<ListedKeyValueProperty> params = new ListedConstructableNode<>(ListedKeyValueProperty.getConstructor(), List.of(longitude, latitude, radius, limit));
//
//
//        ConstructableRootObjectImpl<SourceHttpProperty> root = new ConstructableRootObjectImpl<>(SourceHttpProperty.getConstructor(), List.of(url,
//                method,
//                body,
//                params,
//                headers));
//
//        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(root);
//
//        return new HttpJsonProperties(resolvableSource,
//                MagnitStoreExt.class,
//                mapper,
//                sourceResolver);
//    }

//    @Bean
//    public HttpJsonProperties magnitProducts(SourceHttpResolver sourceResolver,
//                                             JsonMapper mapper,
//                                             ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractedContext) {
//// https://web-gateway.middle-api.magnit.ru/v1/geolocation/store?
//
//
//        //https://web-gateway.middle-api.magnit.ru/v3/goods
//
//        UrlBasicProperty url = new UrlBasicProperty("https", "web-gateway.middle-api.magnit.ru", "/v3/goods");
//
//        HttpMethod method = HttpMethod.POST;
//
//
//        StringProperty body = new StringProperty("{\"categoryIDs\":[],\"includeForAdults\":true,\"onlyDiscount\":false,\"order\":\"desc\",\"pagination\":{\"number\":27,\"size\":36},\"shopType\":\"6\",\"sortBy\":\"price\",\"storeCodes\":[\"992301\"],\"filters\":[{\"id\":\"priceRange\",\"range\":{\"min\":2.59,\"max\":16999.99}}]}");
//
////        ListedConstructableNode<JsonProperty> paginationValue = new ListedConstructableNode<>(JsonProperty.getConstructor(), List.of(new KeyValueSimpleProperty("number", "4"),
////                new KeyValueSimpleProperty("size", "36")));
////
////        KeyValueSimpleProperty categoryIDs = new KeyValueSimpleProperty("categoryIDs", "[]");
////        KeyValueSimpleProperty includeForAdults = new KeyValueSimpleProperty("includeForAdults", "true");
////        KeyValueSimpleProperty onlyDiscount = new KeyValueSimpleProperty("onlyDiscount", "false");
////        KeyValueSimpleProperty order = new KeyValueSimpleProperty("order", "desc");
////        ConstructableNodeImpl<KeyValueSimpleProperty> pagination = new ConstructableNodeImpl<>(KeyValueSimpleProperty.getConstructor(), List.of(new StringProperty("pagination"), paginationValue));
////        KeyValueSimpleProperty shopType = new KeyValueSimpleProperty("shopType", "6");
////        KeyValueSimpleProperty sortBy = new KeyValueSimpleProperty("sortBy", "price");
////        KeyValueSimpleProperty storeCodes = new KeyValueSimpleProperty("storeCodes", "[992301]");
////        KeyValueSimpleProperty filters = new KeyValueSimpleProperty("filters", "[{\"id\":\"priceRange\",\"range\":{\"min\":2.59,\"max\":16999.99}}]");
////
////
////        ListedConstructableNode<JsonProperty> body = new ListedConstructableNode<>(JsonProperty.getConstructor(), List.of(categoryIDs,
////                includeForAdults,
////                onlyDiscount,
////                order,
////                pagination,
////                shopType,
////                sortBy,
////                storeCodes,
////                filters));
//
////        KeyValueSimpleProperty host = new KeyValueSimpleProperty("Host", " web-gateway.middle-api.magnit.ru");
//        KeyValueSimpleProperty userAgent = new KeyValueSimpleProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36");
//        KeyValueSimpleProperty appVersion = new KeyValueSimpleProperty("x-app-version", " 0.1.0");
//        KeyValueSimpleProperty clientName = new KeyValueSimpleProperty("x-client-name", " magnit");
//        KeyValueSimpleProperty deviceId = new KeyValueSimpleProperty("x-device-id", "dgvm5fesvl");
//        KeyValueSimpleProperty deviceTag = new KeyValueSimpleProperty("x-device-tag", "disabled");
//        KeyValueSimpleProperty platformVersion = new KeyValueSimpleProperty("x-platform-version", " window.navigator.userAgent");
//
//        ListedConstructableNode<ListedKeyValueProperty> headers = new ListedConstructableNode<>(ListedKeyValueProperty.getConstructor(), List.of(
//                userAgent,
//                appVersion,
//                clientName,
//                deviceId,
//                deviceTag,
//                platformVersion));
//
//
//        ListedKeyValueProperty params = new ListedKeyValueProperty(List.of());
//
//        ConstructableRootObjectImpl<SourceHttpProperty> root = new ConstructableRootObjectImpl<>(SourceHttpProperty.getConstructor(), List.of(url,
//                method,
//                body,
//                params,
//                headers));
//
//        ResolvableSourceHttpProperties resolvableSource = new ResolvableSourceHttpProperties(root);
//
//        return new HttpJsonProperties(resolvableSource,
//                MagnitProductExt.class,
//                mapper,
//                sourceResolver);
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

}
