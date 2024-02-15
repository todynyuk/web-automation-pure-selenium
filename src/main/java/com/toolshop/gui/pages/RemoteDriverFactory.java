package com.toolshop.gui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriverFactory {
    private static final Logger LOGGER = LogManager.getLogger();

    private static ThreadLocal<WebDriver> remoteDriverThreadLocal = new ThreadLocal<>();

    public static WebDriver createRemoteDriver(String hubUrl, DesiredCapabilities capabilities) {
        try {
            remoteDriverThreadLocal.set(new RemoteWebDriver(new URL(hubUrl), capabilities));
        } catch (MalformedURLException e) {
            LOGGER.error("Problem with selenium server");
            e.printStackTrace();

        }
        return remoteDriverThreadLocal.get();
    }

    public static WebDriver getRemoteDriver() {
        return remoteDriverThreadLocal.get();
    }

    public static void quitRemoteDriver() {
        remoteDriverThreadLocal.get().quit();
        remoteDriverThreadLocal.remove();
    }
}
