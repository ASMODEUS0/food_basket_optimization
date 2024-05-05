package com.example.food_basket_optimization.selenium.util;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Log4j2
public class Util {

    public static WebElement waitUntilDoNotFindElement(By element, int timeout, WebDriver browser) {

        try {
            WebElement foundElement = browser.findElement(element);
            log.info("SELENIUM find element with xpath: " + element.toString());
            return foundElement;
        } catch (Exception e) {
            int currentTiemout = waitOrThrow(timeout);
            return waitUntilDoNotFindElement(element, currentTiemout, browser);
        }
    }


    public static Cookie waitUntilGetCookie(String withName, int timeout, WebDriver browser) {
        Cookie cookie = browser.manage().getCookieNamed(withName);

        if (cookie == null) {
            int currentTimeout = waitOrThrow(timeout);
            return waitUntilGetCookie(withName, currentTimeout, browser);
        }
        log.info("SELENIUM find cookie with name: " + withName);
        return cookie;
    }


    private static int waitOrThrow(int timeout) {
        if (timeout > 0) {
            log.info("SELENIUM waits: " + timeout);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return timeout - 50;
        }
        throw new IllegalArgumentException();
    }



    public static File getProxyExtensions(String addr,
                                           String port,
                                           String login,
                                           String password) {

        String manifest_json = """
                {
                  "version": "1.0.0",
                  "manifest_version": 2,
                  "name": "Chrome Proxy",
                  "permissions": [
                    "proxy",
                    "tabs",
                    "unlimitedStorage",
                    "storage",
                    "<all_urls>",
                    "webRequest",
                    "webRequestBlocking"
                  ],
                  "background": {
                    "scripts": ["background.js"]
                  },
                  "minimum_chrome_version":"22.0.0"
                }""";

        String background_js = String.format("""
                var config = {
                  mode: "fixed_servers",
                  rules: {
                    singleProxy: {
                      scheme: "http",
                      host: "%s",
                      port: parseInt(%s)
                    },
                    bypassList: ["localhost"]
                  }
                };

                chrome.proxy.settings.set({value: config, scope: "regular"}, function() {});

                function callbackFn(details) {
                return {
                authCredentials: {
                username: "%s",
                password: "%s"
                }
                };
                }

                chrome.webRequest.onAuthRequired.addListener(
                callbackFn,
                {urls: ["<all_urls>"]},
                ['blocking']
                );""", addr, port, login, password);


        try {


            String proxyExtensionName = "proxy_auth_plugin.zip";
            deleteIfExists(proxyExtensionName);
            FileOutputStream fos = new FileOutputStream(proxyExtensionName);


            ZipOutputStream zipOS = new ZipOutputStream(fos);

            String mainfestFileName = "manifest.json";
            deleteIfExists(mainfestFileName);
            createFile(mainfestFileName, manifest_json);
            String backgroundFileName = "background.js";
            deleteIfExists(backgroundFileName);
            createFile(backgroundFileName, background_js);

            writeToZipFile("manifest.json", zipOS);
            writeToZipFile("background.js", zipOS);
            zipOS.close();
            fos.close();
            return new File("proxy_auth_plugin.zip");

        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

    }

    private static void deleteIfExists(String path){
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (IOException ignored) {
        }
    }


    private static void writeToZipFile(String path, ZipOutputStream zipStream) throws IOException {
        System.out.println("Writing file : '" + path + "' to zip file");
        File aFile = new File(path);
        FileInputStream fis = new FileInputStream(aFile);
        ZipEntry zipEntry = new ZipEntry(path);
        zipStream.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipStream.write(bytes, 0, length);
        }
        zipStream.closeEntry();
        fis.close();
    }

    private static void createFile(String filename, String text) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(text);
        }
    }
}
