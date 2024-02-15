package com.toolshop.gui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HomePage extends AbstractPage{

    private static final Logger LOGGER = LogManager.getLogger();

    @FindBy(css = "input[data-test='search-query']")
    private WebElement searchInputField;

    @FindBy(css = "button[data-test='search-submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//h5[@data-test='product-name']")
    private List<WebElement> productsNameList;

    @FindBy(xpath = "//h3[contains(text(),'Searched for')]")
    private WebElement searchTitle;

    @FindBy(xpath = "//h4[contains(text(),'By category')]")
    private WebElement byCategoryText;

    @FindBy(xpath = "//h4[contains(text(),'By brand:')]")
    private WebElement byBrandText;

    @FindBy(css = "select[data-test='sort']")
    private WebElement dropdownMenu;

    @FindBy(css = "option[value='price,asc']")
    private WebElement sortAscendingDropdownMenu;

    @FindBy(xpath = "//label[contains(text(),'%s')]")
    private WebElement checkboxByCategory;

    private final String locator = "//label[contains(text(),'%s')]";

    @FindBy(css = "span[data-test='product-price']")
    private List<WebElement> productPriceList;

    @FindBy(css = "a[data-test='nav-sign-in']")
    private WebElement signInButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void searchForProduct(String query) {
        moveToElement(byCategoryText);
        sendKeys(searchInputField, query);
        waitForElementToBeClickable(searchButton);
        click(searchButton);
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
                .filter(s-> s.toLowerCase().contains("hammer"))
                .collect(Collectors.toList());
        filteredList.forEach(System.out::println);
    }

    public void clickOnDropdownMenu() {
        click(dropdownMenu);
    }

    public void sortFromLowToHigh() {
        click(sortAscendingDropdownMenu);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isProductsSortedFromLowToHighPrice() {
        List<Double> priceList = new ArrayList<>();
        for (WebElement element : productPriceList) {
            priceList.add(Double.parseDouble(element.getText().replace("$", "")));
        }
        for (int i = 0; i < priceList.size() - 1; i++) {
            if (priceList.get(i) <= priceList.get(i + 1)) {
                 LOGGER.info("Sort from low to high price: [" + priceList.get(i) + "] [" + priceList.get(i + 1) + "]");
            } else if (priceList.get(i) > priceList.get(i + 1)) {
                LOGGER.error("Sort from low to high price: [" + priceList.get(i) + "] [" + priceList.get(i + 1) + "]");
               return false;
            }
        }
        return true;
    }

    public boolean isProductsSortedFromLowToHighPriceStream() {
        List<Double> priceList = productPriceList.stream()
                .map(element -> Double.parseDouble(element.getText().replace("$", "")))
                .collect(Collectors.toList());

        boolean sorted = IntStream.range(0, priceList.size() - 1)
                .allMatch(i -> priceList.get(i) <= priceList.get(i + 1));

        if (sorted) {
            priceList.forEach(price -> LOGGER.info("Sort from low to high price: [" + price + "]"));
        } else {
            IntStream.range(0, priceList.size() - 1)
                    .filter(i -> priceList.get(i) > priceList.get(i + 1))
                    .forEach(i -> LOGGER.error("Sort from low to high price: [" + priceList.get(i) + "] [" + priceList.get(i + 1) + "]"));
        }

        return sorted;
    }

    public void setCheckboxByCategory(String param){
        moveToElement(byBrandText);
        driver.findElement(By.xpath(String.format(locator, param))).click();
    }

public boolean isChosenNameCorrect(String name) {
    return getProductTitles().contains(name);
}

    public ProductDetailsPage clickOnProductPage(int index){
        productsNameList.get(index).click();
        return new ProductDetailsPage(driver);
    }

    public LoginPage clickOnLoginButton() {
        signInButton.click();
        return new LoginPage(driver);
    }
}
