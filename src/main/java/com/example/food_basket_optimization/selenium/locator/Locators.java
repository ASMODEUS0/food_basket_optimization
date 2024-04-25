package com.example.food_basket_optimization.selenium.locator;

import org.openqa.selenium.By;

public interface Locators {


   interface YahooMainPage {
       By SEARCH_BUTTON = By.xpath("//*[@id=\"ybar-search\"]");
       By SEARCH_FIELD = By.xpath("//*[@id=\"yschsp\"]\n");
   }

   interface YahooCurrentSearchPage{
       By FIRST_LINK_BUTTON = By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div/div/ol/li[1]/div/div[1]/h3/a");

   }


   interface YahooBase{
       By ACCEPT_COOKIE_BUTTON = By.xpath("/html/body/div[1]/div/div/div/form/div[2]/div[2]/button[1]");

   }


}
