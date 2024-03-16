

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.mapping.htmlmap.HtmlMapProperty;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.JsonDataTransformer;
import com.example.food_basket_optimization.extraction.properties.mapping.jsonmap.JsonMapper;
import com.example.food_basket_optimization.extractproperties.extractedentity.lenta.LentaProductExt;
import com.example.food_basket_optimization.extractproperties.extractedentity.lenta.LentaStoreExt;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestConnection {


    @Test
    void test() throws IOException, InterruptedException {


        HttpClient client = HttpClient.newBuilder()
                .build();


        List<String> headers = List.of("Content-Type", "application/json",
                "User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.1 Safari/605.1.15",
                "Cookie", "_ga=GA1.2.1468867256.1709894832; _ga_7T2BMDLJY8=GS1.1.1710538633.13.0.1710538633.0.0.0; _ga_QB4J0GGLM=GS1.1.1710538632.13.0.1710538633.0.0.0; _ga_QB4J0GGLMG=GS1.1.1710538632.11.0.1710538633.0.0.0; _ga_Z9D3HDRHYG=GS1.1.1710538633.13.0.1710538633.0.0.0; _gid=GA1.2.386583094.1710367771; oxxfgh=d9957134-785f-44a1-9d36-6e0c8723f846#0#5184000000#5000#1800000#44965; splid.d58d=229c8796-6447-4d80-973f-b8151db79113.1709894938.11.1710538634.1710535024.7bd155d0-8a0b-4071-9eb3-e5b013dd7315.a3fcd126-a7b4-45ee-a98d-017c643c53fe.37911a2a-753d-4d2d-bdad-7b4abe500541.1710538633935.1; splses.d58d=*; _gat_UA-327775-1=1; LNCatalogNodes=false; LNCatalogRoot=true; LNProduct=false; LNStart=true; tmr_detect=0%7C1710535012833; tmr_lvid=87cbe7a3a367e2c7decdf0f21c383b49; tmr_lvidTS=1709895017246; _ymab_param=jGZCtHJc9uUjuRrOyQrCO9NHPDCsiOv4SZT24CXkYmtOZk78_4siZepb38TpYu3FHM9kQLpHQS9ixSAcoy5kTmIIU8k; _gcl_au=1.1.421374208.1709895017; IsNextSiteAvailable=True; LastUpdate=2024-03-15; qrator_jsid=1710535006.981.SELBe9ImmIyDjcaR-mtm8engbgkpav27enghdiumoo3fhuc4h; DontShowCookieNotification=true; ValidationToken=a0816687e2f79d0d927ff34abf7be1d1; cookiesession1=678B286F2BD7E8C4997E080EEF4E942E; _ym_isad=2; _selectedStoreID=1352; _userID=undefined; _utm_referrer=undefined; ReviewedSkus=041476,550967,535292; ASP.NET_SessionId=hdog52k3yg23plleshurhxu1; AuthorizationMotivationHidden=true; UnAuthorizedNavigationsCount=4; CitySubDomainCookie=spb; DeliveryOptions=Pickup; ShouldSetDeliveryOptions=False; _tm_lt_sid=1709895445974.679665; _ym_d=1709895017; _ym_uid=1709895017515631873; KFP_DID=3622b906-7fd5-6fd2-d7db-85891a6cd629; .ASPXANONYMOUS=e0w7-C7f3CzX8l-VS6P2TZN2882PVVtl6KXV36z46aYlWbBdVpXTHj2ecjK2G0Qn8zVV6QRuUmZWAL_VYA6yDCncSr1m_c3GSPCwciHFLbNjfMLBgTroU1QLb7iElTAJG3ELMw2; CustomerId=ac524e5843064d8e918b2c4812ebfb1b; GACustomerId=db0db0a1e96c410290f75c483a087ba6; StoreSubDomainCookie=0006; CityCookie=spb; Store=0014");

        HttpRequest request = HttpRequest.newBuilder(URI.create("https://lenta.com/api/v1/skus/list"))
                .POST(HttpRequest.BodyPublishers.ofString("{\"nodeCode\":\"g604e486481b04594c32002c67a2b459a\",\"filters\":[],\"typeSearch\":1,\"sortingType\":\"ByPopularity\",\"offset\":48,\"limit\":24,\"updateFilters\":true}"))
                .headers(headers.toArray(new String[]{}))
                .build();

        ArrayList<HttpRequest> httpRequests = new ArrayList<>();

        for(int i = 0; i < 20; i++){
            httpRequests.add(request);
        }

        List<HttpResponse<String>> list = httpRequests.stream().map(request1 -> {
            try {
                Thread.sleep(new Random().nextInt(0, 1100));
                return client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).toList();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
////
//        ObjectMapper om = new ObjectMapper();
//
//
//        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        om.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
//

        JsonMapper jsonMapper = new JsonMapper();
        List<ExtractedEntity> map = jsonMapper.map(List.of(new HtmlMapProperty(response.body(), LentaProductExt.class, new ArrayList<>())));

//        JavaType customClassCollection = om.getTypeFactory().constructCollectionType(List.class, LentaProductExt.class);
//        Object o = om.readValue(response.body(), customClassCollection);
//

        System.out.println("");


    }

}
