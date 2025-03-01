package com.task1.selenium.pages;

import com.task1.models.Product;
import com.task1.selenium.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BaseTest {
    private By productDetailName = By.className("inventory_details_name");
    private By productDetailPrice = By.className("inventory_details_price");
    private By addToCartButton = By.className("btn_inventory");
    private By shoppingCartLink = By.className("shopping_cart_link");

    public ProductPage() {
        super();
    }

    public Product verifyProductDetails() {
        WebElement elementName = wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailName));
        WebElement elementPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailPrice));
        return new Product(elementName.getText(), elementPrice.getText());
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
