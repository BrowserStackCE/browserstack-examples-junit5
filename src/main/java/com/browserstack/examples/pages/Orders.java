package com.browserstack.examples.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Orders extends NavigablePage {

    private static final String ORDERS_ID = "orders";
    private static final String PRODUCT_COST_CLASS = "a-size-small a-color-price";

    private final WebDriver webDriver;

    public Orders(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    @Step("Retrieving order count")
    public int countOrders() throws InterruptedException {
//        webDriver.findElement(By.id(ORDERS_ID)).click();
//        WebDriverWait wait = new WebDriverWait(webDriver, 5000);
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(PRODUCT_COST_CLASS)));
        return webDriver.findElements(By.xpath("//span[@class='a-size-small a-color-price']")).size();
    }

}