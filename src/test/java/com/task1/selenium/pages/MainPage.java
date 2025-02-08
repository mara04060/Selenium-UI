package com.task1.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By productSortContainer = By.className("product_sort_container");
    private By slotId = By.id("select-options");

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void sortProductsBy(String sortValue) {
        WebElement filterElement = wait.until(ExpectedConditions.elementToBeClickable(productSortContainer));
        Select sortSelect = new Select(filterElement);
        sortSelect.selectByValue(sortValue);
    }

    public Map<String, String> findArticleAndGoToPage(String searchText) {
        String xpath = "//div[text()='" + searchText + "']/ancestor::div[@class='inventory_item']";
        WebElement productElement = driver.findElement(By.xpath(xpath));
        String productName = productElement.findElement(By.className("inventory_item_name")).getText();
        String productPrice = productElement.findElement(By.className("inventory_item_price")).getText();
        productElement.findElement(By.className("inventory_item_name")).click();
        return Map.of(
                "Name", productName,
                "Price", productPrice
        );
    }
}
