package com.task1.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class DriverManager {
    private static WebDriver driver;
    private static WebDriverWait wait;

    private DriverManager() {}

    public static WebDriver initDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");   // Headless mode
//        options.addArguments("--disable-gpu"); // Disable GPU --speedWork
            options.addArguments("--window-size=1920x1080"); // Set window size
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);

            // Initialize WebDriverWait
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        return driver;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    // Method to get WebDriverWait
    public static WebDriverWait getWait() {
        if (wait == null) {
            initDriver();
        }
        return wait;
    }

    // Method to quit the driver instance
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }
}