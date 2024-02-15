package com.toolshop;

import com.listeners.ScreenshotListener;
import com.toolshop.gui.pages.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners(ScreenshotListener.class)
public class HomePageTest extends AbstractTest {

    final int INDEX_ZERO = 0;

    @Test(description = "User can search product and check if products results are not empty.")
    public void productSearchTest() {
        HomePage homePage = new HomePage(driver);
        homePage.searchForProduct("Hammer");
        List<String> productTitles = homePage.getProductTitles();
        Assert.assertFalse(productTitles.isEmpty(), "Product search results are empty.");
        homePage.getProductTitleText();
    }

    @Test(description = "User can sort dropdown menu and check if products are sorted.")
    public void sortLowToHighTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnDropdownMenu();
        homePage.sortFromLowToHigh();
        Assert.assertTrue(homePage.isProductsSortedFromLowToHighPriceStream(), "Product price are not sorted");
    }

    @Test(description = "User can use and check checkbox filters.")
    public void checkboxFilterTest() {
        HomePage homePage = new HomePage(driver);
        homePage.setCheckboxByCategory("Pliers");
        Assert.assertTrue(homePage.isChosenNameCorrect("Pliers"), "Product filter are not contains text");
    }
}
