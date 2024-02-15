package com.toolshop.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage extends AbstractPage {

    @FindBy(css = "a[data-test='nav-favorites']")
    private WebElement myFavoritesButton;

    @FindBy(css = "a[data-test='nav-profile']")
    private WebElement profileButton;

    @FindBy(css = "a[data-test='nav-invoices'")
    private WebElement invoicesButton;

    @FindBy(css = "a[data-test='nav-messages']")
    private WebElement messagesButton;

    @FindBy(css = "a[data-test='nav-home']")
    private WebElement homeButton;

    @FindBy(css = "a[id='user-menu']")
    private WebElement userMenu;

    public AccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isFavoritesButtonPresent() {
        return isElementPresent(myFavoritesButton);
    }

    public boolean isProfileButtonPresent() {
        return isElementPresent(profileButton);
    }

    public boolean isInvoicesButtonPresent() {
        return isElementPresent(invoicesButton);
    }

    public boolean isMessagesButtonPresent() {
        return isElementPresent(messagesButton);
    }

    public HomePage clickOnHomePage() {
        waitForVisibilityOfElement(userMenu);
        click(homeButton);
        return  new HomePage(driver);
    }
}
