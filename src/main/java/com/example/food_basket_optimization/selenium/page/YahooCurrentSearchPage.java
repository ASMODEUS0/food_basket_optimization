package com.example.food_basket_optimization.selenium.page;

import com.example.food_basket_optimization.selenium.locator.Locators;
import com.example.food_basket_optimization.selenium.page.UnknownPage;
import com.example.food_basket_optimization.selenium.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

@Slf4j
public class YahooCurrentSearchPage {


    private final WebDriver browser;
    private final WebElement firstLinkButton;
    private final String initSearchLink = "https://search.yahoo.com/search;_ylt=AwrilvsiuSdm_UEHfpRDDWVH;_ylc=X1MDMTE5NzgwNDg2NwRfcgMyBGZyAwRmcjIDcDpzLHY6c2ZwLG06c2ItdG9wBGdwcmlkA2pmWjJ0LmRKUVFpR09aRGl5TGtoZ0EEbl9yc2x0AzAEbl9zdWdnAzIEb3JpZ2luA3NlYXJjaC55YWhvby5jb20EcG9zAzAEcHFzdHIDBHBxc3RybAMwBHFzdHJsAzkEcXVlcnkDbGVudGEuY29tBHRfc3RtcAMxNzEzODc5MzY0?p=";

    public YahooCurrentSearchPage(WebDriver browser) {
        this.browser = browser;
        firstLinkButton = browser.findElement(Locators.YahooCurrentSearchPage.FIRST_LINK_BUTTON);
    }


    public YahooCurrentSearchPage(WebDriver browser, String searchLink) {
        this.browser = browser;
        browser.get(initSearchLink + searchLink);
        log.info("SELENIUM get search with link: " + searchLink);

        firstLinkButton = browser.findElement(Locators.YahooCurrentSearchPage.FIRST_LINK_BUTTON);

    }

    public UnknownPage clickFirstLink() {
        firstLinkButton.click();
        log.info("SELENIUM click first link in search page");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Set<String> windowHandles = browser.getWindowHandles();
        String secondWindowHandler = (String) windowHandles.toArray()[1];
        browser.switchTo().window(secondWindowHandler);
        log.info("SELENIUM switch to the next window");

        return new UnknownPage(browser);
    }





}
