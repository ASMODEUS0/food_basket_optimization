package com.example.food_basket_optimization.proxy;

import ch.qos.logback.core.util.FileUtil;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.*;


public class CheckProxy {


    private void checkProxy() throws IOException {
        HttpClient client = new HttpClient();

        HostConfiguration config = client.getHostConfiguration();
        config.setProxy("someProxyURL", 10);

        Credentials credentials = new UsernamePasswordCredentials("username", "password");
        AuthScope authScope = new AuthScope("someProxyURL", 10);
        client.getState().setProxyCredentials(authScope, credentials);
        String  jsonData =  "{\"slug\":\"diksi_cks39\",\"sort\":\"by_popularity\",\"category_uid\":\"38776\",\"maxDepth\":100}";

        EntityEnclosingMethod method = new PostMethod("https://shop.market.yandex.ru/api/v2/menu/goods?auto_translate=false");
        method.setRequestEntity(new StringRequestEntity( jsonData, "application/json", "utf-8"));

    }



    public static void main(String[] args) throws IOException, InterruptedException {


//      String file =  "/Users/nikitauporov/Desktop/Учеба2024/Диплом/food_basket_optimization/src/main/resources/headers-test.txt";

//        BufferedReader fileBufReader = new BufferedReader(new FileReader(file));
//
//
//        List<String> headers = new ArrayList<>();
//        while(fileBufReader.ready()){
//            String headerKeyValue = fileBufReader.readLine();
//            String[] split = headerKeyValue.split(":");
//            if(split.length == 2){
//                headers.addAll(Arrays.asList(split));
//            }
//        }
//
//


//        InetSocketAddress inetSocketAddress = new InetSocketAddress("38.154.227.167", 5868);
//
//
//
//
//        ProxySelector proxySelector = ProxySelector.of(inetSocketAddress);
//
//        HttpClient client = HttpClient.newBuilder()
//                .version(HttpClient.Version.HTTP_1_1)
//                .proxy(proxySelector)
//                .authenticator(new Authenticator() {
//                    @Override
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication("wlomvegd", "noochmx099qc".toCharArray());
//                    }
//                })
//                .build();
//
//
//
//
//        HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.nic.ru/help/err_tunnel_connection_failed-chto-eto-za-oshibka-i-kak-ee-ispravit6_11179.html"))
//                .GET()
//                .build();
//
//
//        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
//
//        String body = send.body();
//
//
//
//
//        System.out.println("");


    }
}
