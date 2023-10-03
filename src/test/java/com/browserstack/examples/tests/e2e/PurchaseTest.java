package com.browserstack.examples.tests.e2e;

import com.browserstack.examples.helpers.CommonSteps;
import com.browserstack.examples.pages.*;
import com.browserstack.examples.utils.LoggedInNavBarComponent;
import com.browserstack.examples.helpers.Constants;
import com.browserstack.examples.helpers.ElementLocatorUtil;
//import com.browserstack.webdriver.core.WebDriverFactory;
import com.browserstack.webdriver.junit5.extensions.WebDriverTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Feature(Constants.AllureTags.FEATURE_END_TO_END)
public class PurchaseTest {

    private static final String ORDER_PLACED_MESSAGE = "Your Order has been successfully placed.";
    private static final String USER = "fav_user";
    private static final int PRODUCT_COUNT = 3;

    @Severity(SeverityLevel.CRITICAL)
    @Story(Constants.AllureTags.STORY_PURCHASE)
    @WebDriverTest
    public void orderPlacementTest(WebDriver webDriver) throws InterruptedException {
        String homepageUrl = CommonSteps.getHomePageUrl();
        HomePage homePage = new HomePage(homepageUrl, webDriver);
        ElementLocatorUtil.waitUntilTitleIs(webDriver, Constants.ElementLocators.HOME_PAGE_TITLE, Constants.ErrorMessages.HOME_PAGE_NOT_LOADED_ON_TIME);
        ElementLocatorUtil.waitUntilElementVanish(webDriver, By.className(Constants.ElementLocators.RELOAD_SPINNER_CLASS), Constants.ErrorMessages.SPINNER_NOT_STOPPED_ON_TIME);
        Login login = homePage.getNavBarComponent().clickSignIn();
        ElementLocatorUtil.waitUntilElementAppears(webDriver, By.id(Constants.ElementLocators.USER_INPUT_ID), Constants.ErrorMessages.SIGNIN_PAGE_NOT_LOADED_ON_TIME);
        login.loginWithFavUser(USER);
        ElementLocatorUtil.waitUntilElementAppears(webDriver, By.className(Constants.ElementLocators.USERNAME_LABEL_CLASS), Constants.ErrorMessages.SIGNIN_NOT_COMPLETED_ON_TIME);
        Bag bag = homePage.addItemsToCart(PRODUCT_COUNT);
        Checkout checkout = bag.checkout();
        Confirmation confirmation = checkout.clickSubmit();
        String message = confirmation.getConfirmationMessage();
        int count = confirmation.getProductCount();
        Assertions.assertEquals(ORDER_PLACED_MESSAGE, message, Constants.ErrorMessages.CONFIRMATION_FAILED);
        Assertions.assertEquals(PRODUCT_COUNT, count, Constants.ErrorMessages.PRODUCT_COUNT_MISMATCH);
        confirmation.clickOnContinue();
        HomePage newHomePage = new HomePage(login.getNavBarComponent(), webDriver);
        Orders orders = ((LoggedInNavBarComponent) newHomePage.getNavBarComponent()).clickOnOrders();
        count = orders.countOrders();
        Assertions.assertEquals(PRODUCT_COUNT, count, Constants.ErrorMessages.PRODUCT_COUNT_MISMATCH);
    }
}