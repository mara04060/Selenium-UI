package com.task1.selenium.pages;

import com.task1.models.Product;
import com.task1.selenium.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


public class MainPage extends BaseTest {
    private By productSortContainer = By.className("product_sort_container");
    private By inventoryItemName = By.className("inventory_item_name");
    private By inventoryItemPrice = By.className("inventory_item_price");
    private By slotId = By.id("select-options");

    public MainPage() {
            super();
    }

    public void sortProductsBy(String sortValue) {
        WebElement filterElement = wait.until(ExpectedConditions.elementToBeClickable(productSortContainer));
        Select sortSelect = new Select(filterElement);
        sortSelect.selectByValue(sortValue);
    }

    private WebElement productElement(String searchText){
        String xpath = "//div[text()='" + searchText + "']/ancestor::div[@class='inventory_item']";
        return driver.findElement(By.xpath(xpath));
    }

    public Product findArticleAndGoToPage(String searchText) {
        WebElement productElement = productElement(searchText);
        Product product = new Product( productElement.findElement(inventoryItemName).getText(),
                productElement.findElement(inventoryItemPrice).getText() );
        productElement.findElement(inventoryItemName).click();
        return product;
    }
}
