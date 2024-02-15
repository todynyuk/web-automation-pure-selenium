package com.toolshop.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailsPage extends AbstractPage{
    @FindBy(css = "h1[data-test='product-name']")
    private WebElement productTitleText;

    @FindBy(css = "button[id='btn-add-to-cart']")
    private WebElement addToCartButton;

    @FindBy(css = "span[data-test='cart-quantity']")
    private WebElement cartCounter;

    @FindBy(css = "a[data-test='nav-cart']")
    private WebElement cartButton;

    @FindBy(xpath = "//button[@id='btn-add-to-favorites']")
    private WebElement addToFavorites;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addToCartButtonClick() {
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        addToCartButton.click();
    }

    public boolean isCartCounterPresent() {
        return isElementPresent(cartCounter);
    }

    public String getTitleText() {
     return getTitleText(productTitleText);
    }

    public CartPage clickOnCartButton() {
        click(cartButton);
        return new CartPage(driver);
    }

}

