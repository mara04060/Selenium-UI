package com.task1.selenium;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task1.selenium.pages.CartPage;
import com.task1.selenium.pages.LoginPage;
import com.task1.selenium.pages.MainPage;
import com.task1.selenium.pages.ProductPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import com.task1.selenium.config.Config;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MainPageTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl;
    private String username;
    private String password;

    @BeforeMethod
    public void setUp() throws IOException {
        // Read config
        ObjectMapper objectMapper = new ObjectMapper();
        Config config = objectMapper.readValue(new File("src/test/resources/config/config.json"), Config.class);

        baseUrl = config.getBaseUrl();
        username = config.getUsername();
        password = config.getPassword();

        driver = DriverManager.getDriver();
        wait = DriverManager.getWait();
    }

    @Test (dataProviderClass = MainPageDataProvider.class, dataProvider = "positive")
    public void addBasketProduct(String sortProducts, String forSearchArticleName) {
        // Auth
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);

        // Filter Enable
        MainPage mainPage = new MainPage();
        mainPage.sortProductsBy(sortProducts);

        // Find article: Sauce Labs Bike Light
        Map<String, String> product = mainPage.findArticleAndGoToPage(forSearchArticleName);

        // Add to basket
        ProductPage productPage = new ProductPage();
        productPage.verifyProductDetails(product.get("Name"), product.get("Price"));
        productPage.addToCart();

        // Go to basket and verify
        productPage.goToCart();
        CartPage cartPage = new CartPage();
        cartPage.verifyCartCounter(1);
        cartPage.verifyCartItem(product.get("Name"), product.get("Price"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            DriverManager.quitDriver();
        }
    }
}