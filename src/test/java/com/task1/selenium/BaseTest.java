package com.task1.selenium;

import com.task1.utils.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseTest() {
        this.driver = DriverManager.getDriver();
        this.wait = DriverManager.getWait();
        PageFactory.initElements(driver, this);
    }
}