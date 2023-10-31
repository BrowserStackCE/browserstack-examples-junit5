package com.browserstack.examples.helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CommonSteps extends BaseTest {

    public static String getHomePageUrl() {
        return "https://bstackdemo.com";
    }

    @Step("Navigating to the home page")
    public static void navigateToHome() {
        webDriver.get("https://bstackdemo.com");
        ElementLocatorUtil.waitUntilTitleIs(Constants.ElementLocators.HOME_PAGE_TITLE, Constants.ErrorMessages.HOME_PAGE_NOT_LOADED_ON_TIME);
        waitForSpinner();
    }

    @Step("Waiting for the spinner to stop")
    public static void waitForSpinner() {
        ElementLocatorUtil.waitUntilElementVanish(By.className(Constants.ElementLocators.RELOAD_SPINNER_CLASS), Constants.ErrorMessages.SPINNER_NOT_STOPPED_ON_TIME);
    }

    @Step("Finding the product count")
    public static int productCount() {
        return webDriver.findElements(By.className(Constants.ElementLocators.PRODUCT_CARD_CSS)).size();
    }

    @Step("Signing in with username {0} and password {1}")
    public static void signIn(String userName, String password) {
        WebElement signInButton = webDriver.findElement(By.id(Constants.ElementLocators.SIGN_IN_BUTTON_ID));
        signInButton.click();
        ElementLocatorUtil.waitUntilElementAppears(By.id(Constants.ElementLocators.USER_INPUT_ID), Constants.ErrorMessages.SIGNIN_PAGE_NOT_LOADED_ON_TIME);
        WebElement userElement = webDriver.findElement(By.id(Constants.ElementLocators.USER_INPUT_ID));
        userElement.sendKeys(userName);
        userElement.sendKeys(Keys.ENTER);
        WebElement passwordElement = webDriver.findElement(By.id(Constants.ElementLocators.PASSWORD_INPUT_ID));
        passwordElement.sendKeys(password);
        passwordElement.sendKeys(Keys.ENTER);
        WebElement logInButton = webDriver.findElement(By.id(Constants.ElementLocators.LOGIN_BUTTON_ID));
        logInButton.click();
        ElementLocatorUtil.waitUntilElementAppears(By.className(Constants.ElementLocators.USERNAME_LABEL_CLASS), Constants.ErrorMessages.SIGNIN_NOT_COMPLETED_ON_TIME);
    }
}