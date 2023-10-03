package com.browserstack.examples.utils;

import com.browserstack.examples.pages.Orders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoggedInNavBarComponent extends NavBarComponent {

    private static final String ORDERS_BUTTON_ID = "orders";
    private static final String PRODUCT_COST_CLASS = "//span[@class='a-size-small a-color-price']";
    private static final String LOG_OUT_BUTTON_ID = "logout";

    private WebDriver webDriver;

    public LoggedInNavBarComponent(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public Orders clickOnOrders() throws InterruptedException {
        webDriver.findElement(By.id(ORDERS_BUTTON_ID)).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 5000);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='a-size-small a-color-price']")));
        return new Orders(webDriver);
    }

    public void logOut() {
        webDriver.findElement(By.id(LOG_OUT_BUTTON_ID)).click();
    }

}
