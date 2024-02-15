package com.toolshop;

import com.listeners.ScreenshotListener;
import com.toolshop.gui.pages.AbstractTest;
import com.toolshop.gui.pages.CartPage;
import com.toolshop.gui.pages.HomePage;
import com.toolshop.gui.pages.ProductDetailsPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ScreenshotListener.class)
public class ProductDetailsTest extends AbstractTest {
    final int INDEX_ONE = 0;

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
}
