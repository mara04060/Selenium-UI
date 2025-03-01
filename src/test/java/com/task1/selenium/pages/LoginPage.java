package com.task1.selenium.pages;

import com.task1.models.User;
import com.task1.selenium.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BaseTest {
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    public LoginPage() {
        super();
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameField));
        driver.findElement(this.usernameField).sendKeys(username);
    }
    public void enterPassword(String password) {
        wait.until((ExpectedConditions.elementToBeClickable(passwordField)));
        driver.findElement(this.passwordField)
                .sendKeys(password);
    }
    public void clickLoginButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        element.click();
    }

    public MainPage login(User user) {
        enterUsername(user.getUser());
        enterPassword(user.getPassword());
        clickLoginButton();
        return new MainPage();
    }
}
