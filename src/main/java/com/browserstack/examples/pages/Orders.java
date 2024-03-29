package com.browserstack.examples.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Orders extends NavigablePage {

    private static final String ORDERS_ID = "orders";
    private static final String PRODUCT_COST_CLASS = "a-size-small a-color-price";

    private final WebDriver webDriver;

    public Orders(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    @Step("Retrieving order count")
    public int countOrders() {
        webDriver.findElement(By.id(ORDERS_ID)).click();
        return webDriver.findElements(By.className(PRODUCT_COST_CLASS)).size();
    }

}