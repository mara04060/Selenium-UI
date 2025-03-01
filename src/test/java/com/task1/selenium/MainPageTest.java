package com.task1.selenium;

import com.task1.models.Product;
import com.task1.models.User;
import com.task1.selenium.config.Descriptions;
import com.task1.utils.ConfigLoader;
import com.task1.utils.DriverManager;
import com.task1.selenium.pages.CartPage;
import com.task1.selenium.pages.LoginPage;
import com.task1.selenium.pages.MainPage;
import com.task1.selenium.pages.ProductPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;

public class MainPageTest {
    private WebDriver driver;
    private String baseUrl;
    private User user;

    @BeforeMethod
    public void setUp(){
        // Read config
        ConfigLoader config = ConfigLoader.getInstance();
        baseUrl = config.get("page.baseUrl");
        this.user = new User(config.get("page.username"), config.get("page.password"));

        this.driver = DriverManager.getDriver();
        WebDriverWait wait = DriverManager.getWait();
    }

    @Test (dataProviderClass = MainPageDataProvider.class, dataProvider = "positive")
    public void addBasketProduct(String sortProducts, String forSearchArticleName) {
        // Auth
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = loginPage.login(user);

        // Filter Enable
        mainPage.sortProductsBy(sortProducts);

        // Find article: Sauce Labs Bike Light
        Product productExpected = mainPage.findArticleAndGoToPage(forSearchArticleName);

        // Add to basket
        ProductPage productPage = new ProductPage();
        Product productActual = productPage.verifyProductDetails();
        assert productActual.getName().equals(productExpected.getName()) : Descriptions.PRODUCT_NAME_TEXT.getText();
        assert productActual.getPrice().equals(productExpected.getPrice()) : Descriptions.PRODUCT_PRICE_TEXT.getText();
        productPage.addToCart();

        // Go to basket and verify
        productPage.goToCart();
        CartPage cartPage = new CartPage();
        String countCart = cartPage.verifyCartCounter();
        assert countCart.equals("1") : Descriptions.CART_PRODUCT_NOT_TO_CART.getText();
        Product productCart = cartPage.verifyCartItem();
        assert productCart.getName().equals(productExpected.getName()) : Descriptions.CART_PRODUCT_NAME_TEXT.getText();
        assert productCart.getPrice().equals(productExpected.getPrice()) : Descriptions.CART_PRODUCT_PRICE_TEXT.getText();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            DriverManager.quitDriver();
        }
    }
}