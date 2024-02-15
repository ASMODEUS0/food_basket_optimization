package com.example.food_basket_optimization;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestMapping {

    @Test
    void testMapping() throws IOException, ClassNotFoundException {
        Connection connect = Jsoup.connect("https://dixy.ru/catalog/");

        Document document = connect.get();

        Elements select = document.select("a.region-link");
        Elements select1 = select.select("a.region-link");

        String attr = select.attr("data-region-id");

        String str = "com.example.food_basket_optimization.JacksonTest";

        Class<?> aClass = Class.forName(str);
        System.out.println();


    }
}
