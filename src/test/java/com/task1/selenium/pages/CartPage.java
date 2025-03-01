package com.task1.selenium.pages;

import com.task1.models.Product;
import com.task1.selenium.BaseTest;
import com.task1.selenium.config.Descriptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BaseTest {
    private By cartItemLabel = By.className("cart_item_label");
    private By cartItemName = By.className("inventory_item_name");
    private By cartItemPrice = By.className("inventory_item_price");
    private By cartCounter = By.className("shopping_cart_badge");

    public CartPage() {
       super();
    }

    public Product verifyCartItem() {
        WebElement elementName = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(driver.findElement(cartItemLabel), cartItemName));
        WebElement filterElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(driver.findElement(cartItemLabel), cartItemPrice));
        return new Product( elementName.getText(), filterElement.getText() );
    }

    public String verifyCartCounter() {
        WebElement cartCountElement = wait.until(ExpectedConditions.presenceOfElementLocated(cartCounter));
        return cartCountElement.getText();
    }
}
