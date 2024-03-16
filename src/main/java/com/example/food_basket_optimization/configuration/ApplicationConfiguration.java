package com.example.food_basket_optimization.configuration;


import com.example.food_basket_optimization.extraction.properties.base.multi.ContextualStringProperty;
import com.example.food_basket_optimization.extraction.properties.base.multi.MultiString;
import com.example.food_basket_optimization.extraction.properties.base.multi.MultiStringProperty;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.*;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.SimpleMultiUrl;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlMultiPath;
import com.example.food_basket_optimization.extraction.properties.util.RefValue;
import com.example.food_basket_optimization.extractproperties.extractedentity.lenta.LentaCityExt;
import com.example.food_basket_optimization.extractproperties.extractedentity.lenta.LentaProductExt;
import com.example.food_basket_optimization.extractproperties.extractedentity.lenta.LentaStoreExt;
import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.JsonMapper;
import com.example.food_basket_optimization.extraction.properties.root.HttpJsonProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.ResolvableSourceHttpProperties;
import com.example.food_basket_optimization.extraction.properties.sourceresolver.SourceHttpResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


@Configuration
public class ApplicationConfiguration {


//
//
//    @Bean
//    public HttpJsonProperties lentaStoresP(JsonMapper jsonMapper, SourceHttpResolver sourceHttpResolver) {
//
//        return null;
//    }

//    @Bean
//    public ResolvableSourceHttpProperties lentaStoresSourceHttpResolvableP() {
////        new KeyValueUrlMultiUnion()
////        return new ResolvableSourceHttpProperties(new ArrayList<>(),
////                new ArrayList<>(),
////                HttpMethod.GET,
////                lentaStoresUrlP(),
////                "");
//
//        return null;
//    }


//    @Bean
//    public ResolvableSourceHttpProperties lentaCitySourceHttpResolvableP() {
//
//        KeyValueUrlMultiUnion params = new KeyValueUrlMultiUnion(new ArrayList<>());
//        KeyValueUrlMultiUnion headers = new KeyValueUrlMultiUnion(new ArrayList<>());
//        MultiStringProperty body = new MultiStringProperty("");
//        UrlMultiPath url = new UrlMultiPath("", "", new ArrayList<>());
//
//        new ResolvableSourceHttpProperties(params,
//                headers,
//                HttpMethod.GET,
//                url,
//                body);
//
//        return null;
//
//    }

//    @Bean
//    public UrlContextualPathMultiProperties lentaStoresUrlP() {
//        return new UrlContextualPathMultiProperties("https",
//                List.of("api", "v2", "cities", new RefValue(LentaCityExt.class, "id").toString(), "stores"),
//                "lenta.com",
//                extractContext());
//    }

    //    @Bean
//    public UrlBasicProperties lentaCityUrlP() {
//        try {
//            return new UrlBasicProperties(new URL("https://lenta.com/api/v1/cities"));
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    @Bean
    public ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext() {
        return new ConcurrentHashMap<>();
    }


    @Bean
    public HttpJsonProperties lentaCityProperties(JsonMapper jsonMapper, SourceHttpResolver sourceHttpResolver) {
        //https://lenta.com/api/v1/cities

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

    //https://lenta.com/api/v2/cities/Citis.id/stores
    @Bean
    public HttpJsonProperties lentaShopProperties(JsonMapper mapper, SourceHttpResolver resolver) {

        UrlMultiPath multiUrl = new UrlMultiPath("https", "lenta.com", List.of(new MultiStringProperty("api"),
                new MultiStringProperty("v2"),
                new MultiStringProperty("cities"),
                new ContextualStringProperty("", extractContext(), new RefValue(LentaCityExt.class, "id")),
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
    public HttpJsonProperties lentaProductProperties(JsonMapper mapper, SourceHttpResolver resolver) {
        KeyValueUrlMultiUnionProperties emptyProperties = new KeyValueUrlMultiUnion(new ArrayList<>());

        KeyValueUrlMultiUnion headers = new KeyValueUrlMultiUnion(List.of(
                new KeyValueUrlContextual("Cookie", "Store=", extractContext(), new RefValue(LentaStoreExt.class, "id")),
                new SimpleKeyValueUrlMulti("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.1 Safari/605.1.15"),
                new SimpleKeyValueUrlMulti("Accept-Language", "ru"),
                new SimpleKeyValueUrlMulti("Content-Type", "application/json"),
                new SimpleKeyValueUrlMulti("Cookie", "_ga=GA1.2.1468867256.1709894832; _ga_7T2BMDLJY8=GS1.1.1710538633.13.0.1710538633.0.0.0; _ga_QB4J0GGLM=GS1.1.1710538632.13.0.1710538633.0.0.0; _ga_QB4J0GGLMG=GS1.1.1710538632.11.0.1710538633.0.0.0; _ga_Z9D3HDRHYG=GS1.1.1710538633.13.0.1710538633.0.0.0; _gid=GA1.2.386583094.1710367771; oxxfgh=d9957134-785f-44a1-9d36-6e0c8723f846#0#5184000000#5000#1800000#44965; splid.d58d=229c8796-6447-4d80-973f-b8151db79113.1709894938.11.1710538634.1710535024.7bd155d0-8a0b-4071-9eb3-e5b013dd7315.a3fcd126-a7b4-45ee-a98d-017c643c53fe.37911a2a-753d-4d2d-bdad-7b4abe500541.1710538633935.1; splses.d58d=*; _gat_UA-327775-1=1; LNCatalogNodes=false; LNCatalogRoot=true; LNProduct=false; LNStart=true; tmr_detect=0%7C1710535012833; tmr_lvid=87cbe7a3a367e2c7decdf0f21c383b49; tmr_lvidTS=1709895017246; _ymab_param=jGZCtHJc9uUjuRrOyQrCO9NHPDCsiOv4SZT24CXkYmtOZk78_4siZepb38TpYu3FHM9kQLpHQS9ixSAcoy5kTmIIU8k; _gcl_au=1.1.421374208.1709895017; IsNextSiteAvailable=True; LastUpdate=2024-03-15; qrator_jsid=1710535006.981.SELBe9ImmIyDjcaR-mtm8engbgkpav27enghdiumoo3fhuc4h; DontShowCookieNotification=true; ValidationToken=a0816687e2f79d0d927ff34abf7be1d1; cookiesession1=678B286F2BD7E8C4997E080EEF4E942E; _ym_isad=2; _selectedStoreID=1352; _userID=undefined; _utm_referrer=undefined; ReviewedSkus=041476,550967,535292; ASP.NET_SessionId=hdog52k3yg23plleshurhxu1; AuthorizationMotivationHidden=true; UnAuthorizedNavigationsCount=4; CitySubDomainCookie=spb; DeliveryOptions=Pickup; ShouldSetDeliveryOptions=False; _tm_lt_sid=1709895445974.679665; _ym_d=1709895017; _ym_uid=1709895017515631873; KFP_DID=3622b906-7fd5-6fd2-d7db-85891a6cd629; .ASPXANONYMOUS=e0w7-C7f3CzX8l-VS6P2TZN2882PVVtl6KXV36z46aYlWbBdVpXTHj2ecjK2G0Qn8zVV6QRuUmZWAL_VYA6yDCncSr1m_c3GSPCwciHFLbNjfMLBgTroU1QLb7iElTAJG3ELMw2; CustomerId=ac524e5843064d8e918b2c4812ebfb1b; GACustomerId=db0db0a1e96c410290f75c483a087ba6; StoreSubDomainCookie=0006; CityCookie=spb")));

        LinkedHashMap<String, MultiString> jsonElements = new LinkedHashMap<>();
        jsonElements.put("nodeCode", new MultiStringProperty("geee7643ec01603a5db2cf4819de1a033"));
        jsonElements.put("filters", new MultiStringProperty(""));
        jsonElements.put("typeSearch", new MultiStringProperty("1"));
        jsonElements.put("sortingType", new MultiStringProperty("ByPopularity"));
        jsonElements.put("offset", new MultiStringProperty("0"));
        jsonElements.put("limit", new MultiStringProperty("48"));
        jsonElements.put("updateFilters", new MultiStringProperty("true"));


//        JsonMultiBodyProperties body = new JsonMultiBodyProperties(jsonElements);
        MultiStringProperty body = new MultiStringProperty("{\"nodeCode\":\"g604e486481b04594c32002c67a2b459a\",\"filters\":[],\"typeSearch\":1,\"sortingType\":\"ByPopularity\",\"offset\":48,\"limit\":24,\"updateFilters\":true}");

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


//    public ResolvableSourceHttpProperties lentaProductResolvableSourceHttp() {
//        return null;
////        return new ResolvableSourceHttpProperties()
//    }
//
//
//    public HttpJsonProperties lentaProductP(JsonMapper jsonMapper, SourceHttpResolver sourceHttpResolver) {
//        return null;
////        return new HttpJsonProperties()
//    }

//    public ResolvableSourceHttpProperties lentaProductResolvableSourceHttp(){
//        KeyValueUrlBasic header1 = new KeyValueUrlBasic("Cookie", "1709895014.056.MefdiwfneJbsCWaT-h96p3f5nt60fjs04j748acfqvgt4s8np;");
//        KeyValueUrlContextual header2 = new KeyValueUrlContextual("Cookie", "Store=", extractContext(), new RefValue(LentaStoreExt.class, "id"));
//        return new ResolvableSourceHttpProperties(new ArrayList<>(),
//                List.of(header1, header2),
//                HttpMethod.GET,
//                new UrlBasicProperties("https", "/catalog/alkogolnye-napitki/", "lenta.com"),
//                "");
//    }
//
//    public HttpHtmlProperties lentaProductP(HtmlMapper htmlMapper, SourceHttpResolver sourceHttpResolver){
//        return new HttpHtmlProperties(sourceHttpResolver,
//                htmlMapper,
//                lentaProductResolvableSourceHttp(),
//                LentaProductExt.class);
//    }


}
