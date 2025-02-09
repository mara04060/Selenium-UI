package com.task1.selenium.config;

public enum Descriptions {
    PRODUCT_NAME_TEXT(10, "Назви товару не збігаються."),
    PRODUCT_PRICE_TEXT(20, "Ціни товару не збігаються."),
    CART_PRODUCT_NAME_TEXT(30, "Назви товару в корзині не збігаються."),
    CART_PRODUCT_PRICE_TEXT(40, "Ціни товару в корзині не збігаються."),
    CART_PRODUCT_NOT_TO_CART(50, "Товар не додано до корзини."),
    ERROR_DOWNLOAD_FILE(60, "Помилка завантаження Файлу file: ");

    private String text;
    private int number;

    public String getText() {
        return text;
    }

    public int getNumber() {
        return number;
    }

    Descriptions(int number, String text) {
        this.text = text;
        this.number = number;
    }
}