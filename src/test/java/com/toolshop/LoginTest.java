package com.toolshop;

import com.listeners.ScreenshotListener;
import com.toolshop.gui.pages.AbstractTest;
import com.toolshop.gui.pages.AccountPage;
import com.toolshop.gui.pages.HomePage;
import com.toolshop.gui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Listeners(ScreenshotListener.class)
public class LoginTest extends AbstractTest {

    @Test(description = "User can login")
    public void LoginTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickOnLoginButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        AccountPage accountPage = loginPage.login("henrysmith555@gmail.com", "henry555");
        Assert.assertTrue(accountPage.isFavoritesButtonPresent(), "Favorites button isn`t presented");
        Assert.assertTrue(accountPage.isProfileButtonPresent(), "Profile button isn`t presented");
        Assert.assertTrue(accountPage.isInvoicesButtonPresent(), "Invoices button isn`t presented");
        Assert.assertTrue(accountPage.isMessagesButtonPresent(), "Messages button isn`t presented");

    }
}
