package com.task1.selenium;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class MainPageTest {
    private WebDriver driver;
    private String baseUrl;
    private String username;
    private String password;

    @BeforeMethod
    public void setUp() throws IOException {
        // Read config
        ObjectMapper objectMapper = new ObjectMapper();
        Config config = objectMapper.readValue(new File("config/config.json"), Config.class);

        baseUrl = config.baseUrl;
        username = config.username;
        password = config.password;

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }


    @Test
    public void search() {
        // Auth
       auth();
        // Filter Enable
       filter();
        // Find article: Sauce Labs Bike Light
        // Equals article
        Map<String, String> product = findArticleAndGoToPage("Sauce Labs Bike Light");
        // Add to basket
        findArticleBasket(product);
        // Testig basket counter
        // Go to basket
        apruveBasketCounter();
        // Equals Name article in basket
        equalsArticleInBasket(product);

    }

    private void auth(){
        driver.get(baseUrl);
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }
    private void filter() {
        Select sortSelect = new Select(driver.findElement(By.className("product_sort_container")));
        sortSelect.selectByValue("hilo");
    }

    private Map<String, String> findArticleAndGoToPage(String searchText){
        String xpath = "//div[text()='"+ searchText +"']/ancestor::div[@class='inventory_item']";
        WebElement productElement = driver.findElement(By.xpath(xpath));
        String productName = productElement.findElement(By.className("inventory_item_name")).getText();
        String productPrice = productElement.findElement(By.className("inventory_item_price")).getText();
        productElement.findElement(By.className("inventory_item_name")).click();
        return Map.of(
                "Name", productName,
                "Price", productPrice
        );
    }
    private void findArticleBasket(Map<String , String> product){
        String productDetailName = driver.findElement(By.className("inventory_details_name")).getText();
        String productDetailPrice = driver.findElement(By.className("inventory_details_price")).getText();

        Assert.assertEquals(product.get("Name"), productDetailName, "Назви товару не збігаються.");
        Assert.assertEquals(product.get("Price"), productDetailPrice, "Ціни товару не збігаються.");
        driver.findElement(By.className("btn_inventory")).click();
        //Add to basket
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    private void apruveBasketCounter(){
        String cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(cartCount, "1", "Товар не додано до корзини.");
    }
    private void equalsArticleInBasket(Map<String , String> product){
        String cartProductName = driver.findElement(By.className("cart_item_label")).findElement(By.className("inventory_item_name")).getText();
        String cartProductPrice = driver.findElement(By.className("cart_item_label")).findElement(By.className("inventory_item_price")).getText();

        Assert.assertEquals(product.get("Name"), cartProductName, "Назви товару в корзині не збігаються.");
        Assert.assertEquals(product.get("Price"), cartProductPrice, "Ціни товару в корзині не збігаються.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
