package com.browserstack.examples.utils;

import com.browserstack.examples.pages.Orders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoggedInNavBarComponent extends NavBarComponent {

    private static final String ORDERS_BUTTON_ID = "orders";
    private static final String PRODUCT_COST_CLASS = "a-size-small a-color-price";
    private static final String LOG_OUT_BUTTON_ID = "logout";

    private WebDriver webDriver;

    public LoggedInNavBarComponent(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public Orders clickOnOrders() {
        webDriver.findElement(By.id(ORDERS_BUTTON_ID)).click();
        WebDriverWaitUtil.getWebDriverWait(webDriver).until(webDriver -> !webDriver.findElements(By.className(PRODUCT_COST_CLASS)).isEmpty());
        return new Orders(webDriver);
    }

    public void logOut() {
        webDriver.findElement(By.id(LOG_OUT_BUTTON_ID)).click();
    }

}
