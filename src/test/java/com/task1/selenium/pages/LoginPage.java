package com.task1.selenium.pages;

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

    public void login(String username, String password) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        element.click();
    }
}
