package com.task1.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By productDetailName = By.className("inventory_details_name");
    private By productDetailPrice = By.className("inventory_details_price");
    private By addToCartButton = By.className("btn_inventory");
    private By shoppingCartLink = By.className("shopping_cart_link");

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void verifyProductDetails(String expectedName, String expectedPrice) {
        WebElement elementName = wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailName));
        WebElement elementPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailPrice));

        String productDetailNameText = elementName.getText();
        String productDetailPriceText = elementPrice.getText();

        assert productDetailNameText.equals(expectedName) : "Назви товару не збігаються.";
        assert productDetailPriceText.equals(expectedPrice) : "Ціни товару не збігаються.";
    }

    public void addToCart() {
        WebElement addCart = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
        addCart.click();
    }

    public void goToCart() {
        WebElement shopingCart = wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartLink));
        shopingCart.click();
    }
}
