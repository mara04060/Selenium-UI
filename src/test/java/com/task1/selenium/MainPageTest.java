package com.task1.selenium;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task1.selenium.pages.CartPage;
import com.task1.selenium.pages.LoginPage;
import com.task1.selenium.pages.MainPage;
import com.task1.selenium.pages.ProductPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import com.task1.selenium.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
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


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");   // Headless mode
        options.addArguments("--disable-gpu"); // Disable GPU --speedWork
        options.addArguments("--window-size=1920x1080"); // Set windows (default= 800x600)

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        // Global setting wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @DataProvider()
    public static Object[][] positive() {
        return new Object[][]{
                //sortProduct, NameArticle for search,
                {"hilo", "Sauce Labs Bike Light"},
                {"za", "Test.allTheThings() T-Shirt (Red)"}
        };
    }
    @Test (dataProvider = "positive")
    public void addToBasketProduct(String sortProducts, String forSearchArticleName) {
        // Auth
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login(username, password);

        // Filter Enable
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.sortProductsBy(sortProducts);

        // Find article: Sauce Labs Bike Light
        Map<String, String> product = mainPage.findArticleAndGoToPage(forSearchArticleName);

        // Add to basket
        ProductPage productPage = new ProductPage(driver, wait);
        productPage.verifyProductDetails(product.get("Name"), product.get("Price"));
        productPage.addToCart();

        // Go to basket and verify
        productPage.goToCart();
        CartPage cartPage = new CartPage(driver, wait);
        cartPage.verifyCartCounter(1);
        cartPage.verifyCartItem(product.get("Name"), product.get("Price"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}