package com.example.food_basket_optimization.selenium.util;

import org.openqa.selenium.chrome.ChromeOptions;

public class DriverOptions {
    public static ChromeOptions defaultParseChromeDriverOptions() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless"); // Run in headless mode
        options.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");
        options.addArguments("--Sec-Ch-Ua=" +
                             "\"Google Chrome\";v=\"123\", \"Not:A-Brand\";v=\"8\", \"Chromium\";v=\"123\"");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("useAutomationExtension", false);
        return options;
    }
}
