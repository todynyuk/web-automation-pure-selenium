package com.toolshop;

import com.listeners.ScreenshotListener;
import com.toolshop.gui.pages.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Listeners(ScreenshotListener.class)
public class CartTest extends AbstractTest {

    final int INDEX_ONE = 1;
    final int INDEX_TWO = 2;

    @Test(description = "User can add products to cart")
    public void addProductToCartTest() {
        HomePage homePage = new HomePage(driver);
        ProductDetailsPage detailsPage = homePage.clickOnProductPage(INDEX_ONE);
        detailsPage.addToCartButtonClick();
        Assert.assertTrue(detailsPage.isCartCounterPresent(), " Counter isn`t presented");
        String titleText = detailsPage.getTitleText();
        CartPage cardPage = detailsPage.clickOnCartButton();
        Assert.assertTrue(cardPage.getTitleText().contains(titleText), " Card title text is not contains" +
                " product title text");
    }

    @Test(description = "User can login and checkout chosen products")
    public void checkoutProductTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickOnLoginButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        AccountPage accountPage = loginPage.login("henrysmith555@gmail.com", "henry555");
        accountPage.clickOnHomePage();
        ProductDetailsPage detailsPage = homePage.clickOnProductPage(INDEX_TWO);
        detailsPage.addToCartButtonClick();
        CartPage cardPage = detailsPage.clickOnCartButton();
        cardPage.clickToCheckoutButton(cardPage.getConfirmButton());
        Assert.assertTrue(cardPage.isElementPresent(cardPage.getLoggedInText()), " Logged in text is not presented");
        cardPage.clickToCheckoutButton(cardPage.getConfirmSingInButton());
        Assert.assertTrue(cardPage.isElementPresent(cardPage.getAddressInputField()), " Address input is not presented");
        cardPage.clickToCheckoutButton(cardPage.getConfirmAddressButton());
        cardPage.fillPaymentMethod("Henry","13101090355");
        Assert.assertTrue(cardPage.isElementPresent(cardPage.getSuccessPaymentText()), " Success payment text is not presented");
    }

}
