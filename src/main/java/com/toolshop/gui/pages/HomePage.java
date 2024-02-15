package com.toolshop.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage {

    protected WebDriver driver;

    @FindBy(css = "input[data-test='search-query']")
    private WebElement searchInputField;

    @FindBy(css = "button[data-test='search-submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//h3[contains(text(),'Searched for')]")
    private WebElement searchTitle;

    @FindBy(xpath = "//h5[@data-test='product-name']")
    private List<WebElement> productsNameList;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage searchChosenProduct(String query) {
        searchInputField.sendKeys(query);
        WebElement dynamicElement = (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-test='search-submit']")));
        searchButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new HomePage(driver);
    }

    public List<String> getProductTitles() {
        List<String> titles = new ArrayList<>();
        for (WebElement product : productsNameList) {
            titles.add(product.getText());
        }
        return titles;
    }

    public void getProductTitleText() {
        List<String> productNameListTexts = getProductTitles();
        List<String> filteredList = productNameListTexts.stream()
                .filter(s -> s.toLowerCase().contains("hammer"))
                .collect(Collectors.toList());
        filteredList.forEach(System.out::println);
    }
}
