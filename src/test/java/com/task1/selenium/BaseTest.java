package com.task1.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseTest() {
        this.driver = DriverManager.getDriver();
        this.wait = DriverManager.getWait();
        PageFactory.initElements(driver, this);
    }
}