package com.task1.selenium.pages;

import com.task1.selenium.BaseTest;
import com.task1.selenium.Descriptions;
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

    public void verifyCartItem(String expectedName, String expectedPrice) {
        WebElement elementName = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(driver.findElement(cartItemLabel), cartItemName));
        WebElement filterElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(driver.findElement(cartItemLabel), cartItemPrice));

        String cartProductName = elementName.getText();
        String cartProductPrice = filterElement.getText();

        assert cartProductName.equals(expectedName) : Descriptions.CART_PRODUCT_NAME_TEXT.getText();
        assert cartProductPrice.equals(expectedPrice) : Descriptions.CART_PRODUCT_PRICE_TEXT.getText();
    }

    public void verifyCartCounter(int expectedCount) {
        WebElement cartCountElement = wait.until(ExpectedConditions.presenceOfElementLocated(cartCounter));
        String cartCount = cartCountElement.getText();
        assert cartCount.equals(String.valueOf(expectedCount)) : Descriptions.CART_PRODUCT_NOT_TO_CART.getText();
    }
}
