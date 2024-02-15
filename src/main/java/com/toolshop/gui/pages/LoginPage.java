package com.toolshop.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage{
    @FindBy(css = "input[data-test='email']")
    private WebElement emailInputField;

    @FindBy(css = "input[data-test='password']")
    private WebElement passwordInputField;

    @FindBy(css = "input[data-test='login-submit']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public AccountPage login(String email, String password) {
        emailInputField.click();
        sendKeys(emailInputField,email);
        passwordInputField.click();
        sendKeys(passwordInputField,password);
        click(loginButton);
        return  new AccountPage(driver);
    }
}
