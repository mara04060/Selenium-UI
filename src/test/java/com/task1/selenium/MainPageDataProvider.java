package com.task1.selenium;

import org.testng.annotations.DataProvider;

public class MainPageDataProvider {
    @DataProvider()
    public static Object[][] positive() {
        return new Object[][]{
                //sortProduct, NameArticle for search,
                {"hilo", "Sauce Labs Bike Light"},
                {"za", "Test.allTheThings() T-Shirt (Red)"}
        };
    }
}
