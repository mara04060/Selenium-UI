package com.task1.selenium.pages;

import com.task1.selenium.BaseTest;
import com.task1.selenium.Descriptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BaseTest {
    private By productDetailName = By.className("inventory_details_name");
    private By productDetailPrice = By.className("inventory_details_price");
    private By addToCartButton = By.className("btn_inventory");
    private By shoppingCartLink = By.className("shopping_cart_link");

    public ProductPage() {
        super();
    }

    public void verifyProductDetails(String expectedName, String expectedPrice) {
        WebElement elementName = wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailName));
        WebElement elementPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailPrice));

        String productDetailNameText = elementName.getText();
        String productDetailPriceText = elementPrice.getText();

        assert productDetailNameText.equals(expectedName) : Descriptions.PRODUCT_NAME_TEXT.getText();
        assert productDetailPriceText.equals(expectedPrice) : Descriptions.PRODUCT_PRICE_TEXT.getText();
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
