package com.example.food_basket_optimization.selenium.page;

import com.example.food_basket_optimization.selenium.locator.Locators;
import com.example.food_basket_optimization.selenium.page.YahooCurrentSearchPage;
import com.example.food_basket_optimization.selenium.util.Util;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
@Log4j2
public class YahooMainPage {

    private final WebDriver browser;
    private final WebElement searchField;
    private final String url = "https://search.yahoo.com/";



    public YahooMainPage(WebDriver browser) {
        if (browser == null) {
            throw new IllegalArgumentException();
        }
        this.browser = browser;
        browser.get(url);
        log.info("Selenium get page with url" + url);
        browser.manage().deleteAllCookies();
//        acceptCookie(10000);

        this.searchField = browser.findElement(Locators.YahooMainPage.SEARCH_FIELD);

    }


    private void acceptCookie(Integer timeout){
        log.info("SELENIUM try to accepting cookie in page with url: " + url);
        WebElement acceptCookieButton = Util.waitUntilDoNotFindElement(Locators.YahooBase.ACCEPT_COOKIE_BUTTON, timeout, browser);
        acceptCookieButton.click();

    }


    public YahooCurrentSearchPage enterSearchWordAndClickSearch(String word) {
        searchField.sendKeys(word);
        log.info("SELENIUM send word: " + word + "in search field of page with url:" + url);
        searchField.sendKeys(Keys.ENTER);
        log.info("SELENIUM click enter");
        return new YahooCurrentSearchPage(browser);
    }


}
