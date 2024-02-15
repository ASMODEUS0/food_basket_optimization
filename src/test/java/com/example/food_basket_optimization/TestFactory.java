package com.example.food_basket_optimization;

import com.example.food_basket_optimization.importer.parser.parsedproperties.HtmlMapper;
import com.example.food_basket_optimization.pojo.DiksiProduct;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.List;
import java.util.Optional;

public class TestFactory {




    @Test
    public void testFactory() throws IOException {

        HttpClient httpClient =  HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

//        HttpRequest request = HttpRequest.newBuilder(URI.create("https://dixy.ru/catalog/")).GET().build();

        Connection connect = Jsoup.connect("https://dixy.ru/catalog/");
        Document document = connect.get();
        Elements select = document.select("div.product-container");
        Element first = select.last();
        String title = first.select("div.dixyCatalogItem__title").text();
        String price = first.select("p[content]").first().text();

//        HtmlMapper htmlMapper = new HtmlMapper(context);
//        Optional<List<Object>> map = htmlMapper.map(document, DiksiProduct.class);

        System.out.println("");


        System.out.println("");



    }
}
