package com.task1.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By cartItemLabel = By.className("cart_item_label");
    private By cartItemName = By.className("inventory_item_name");
    private By cartItemPrice = By.className("inventory_item_price");
    private By cartCounter = By.className("shopping_cart_badge");

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void verifyCartItem(String expectedName, String expectedPrice) {
        WebElement elementName = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(driver.findElement(cartItemLabel), cartItemName));
        WebElement filterElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(driver.findElement(cartItemLabel), cartItemPrice));

        String cartProductName = elementName.getText();
        String cartProductPrice = filterElement.getText();

        assert cartProductName.equals(expectedName) : "Назви товару в корзині не збігаються.";
        assert cartProductPrice.equals(expectedPrice) : "Ціни товару в корзині не збігаються.";
    }

    public void verifyCartCounter(int expectedCount) {
        WebElement cartCountElement = wait.until(ExpectedConditions.presenceOfElementLocated(cartCounter));
        String cartCount = cartCountElement.getText();
        assert cartCount.equals(String.valueOf(expectedCount)) : "Товар не додано до корзини.";
    }
}
