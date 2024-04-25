package com.example.food_basket_optimization.selenium.page;

import com.example.food_basket_optimization.selenium.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
@Slf4j
public class UnknownPage {
    public final WebDriver browser;

    public UnknownPage(WebDriver browser) {
        this.browser = browser;
    }


    public Cookie getCookie(String withName){
        log.info("SELENIUM try to get cookie with name: "+ withName);
        return Util.waitUntilGetCookie(withName, 10000, browser);
    }
}
