package com.toolshop;

import com.toolshop.gui.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebSampleTest {
    protected WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/tarasodynyuk/Downloads/chromedriver-mac-x64/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://practicesoftwaretesting.com/#/");
    }

    @Test
    public void testSearchProducts() {
        HomePage homePage = new HomePage(driver);
        homePage.searchChosenProduct("Hammer");
        List<String> productTitles = homePage.getProductTitles();
        Assert.assertFalse(productTitles.isEmpty(), "Product search results are empty.");
       homePage.getProductTitleText();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
