package com.browserstack.examples.helpers;

import com.browserstack.examples.utils.WebDriverWaitUtil;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ElementLocatorUtil extends BaseTest {

    public static void waitUntilTitleIs(String title, String timeOutMessage) {
        try {
            WebDriverWaitUtil.getWebDriverWait(webDriver).until(ExpectedConditions.titleIs(title));
        } catch (TimeoutException e) {
            Assertions.fail(timeOutMessage);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    public static void waitUntilElementVanish(By by, String timeOutMessage) {
        try {
            WebDriverWaitUtil.getWebDriverWait(webDriver).until(waitWebDriver -> waitWebDriver.findElements(by).isEmpty());
        } catch (TimeoutException e) {
            Assertions.fail(timeOutMessage);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    public static void waitUntilURLContains(String url, String timeOutMessage) {
        try {
            WebDriverWaitUtil.getWebDriverWait(webDriver).until(ExpectedConditions.urlContains(url));
        } catch (TimeoutException e) {
            Assertions.fail(timeOutMessage);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    public static void waitUntilElementAppears(By by, String timeOutMessage) {
        try {
            WebDriverWaitUtil.getWebDriverWait(webDriver).until(waitWebDriver -> !waitWebDriver.findElements(by).isEmpty());
        } catch (TimeoutException e) {
            Assertions.fail(timeOutMessage);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}