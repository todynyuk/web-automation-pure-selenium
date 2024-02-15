package com.toolshop.gui.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public abstract class AbstractTest {
    protected WebDriver driver;

    @BeforeTest
    public void setupRemoteWebDriver() {
        String hubUrl = "http://localhost:4444/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        RemoteDriverFactory.createRemoteDriver(hubUrl, capabilities);
    }

    @BeforeMethod
    public void initializeRemoteDriver() {
        driver = RemoteDriverFactory.getRemoteDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://practicesoftwaretesting.com/#");
    }

    @AfterMethod
    public void tearDownRemoteDriver() {
        RemoteDriverFactory.quitRemoteDriver();
    }

}
