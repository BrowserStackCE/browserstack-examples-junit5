package com.browserstack.examples.tests.e2e;

import com.browserstack.examples.helpers.BaseTest;
import com.browserstack.examples.helpers.CommonSteps;
import com.browserstack.examples.pages.*;
import com.browserstack.examples.utils.LoggedInNavBarComponent;
import com.browserstack.examples.helpers.Constants;
import com.browserstack.examples.helpers.ElementLocatorUtil;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

@Feature(Constants.AllureTags.FEATURE_END_TO_END)
public class PurchaseTest extends BaseTest {

    private static final String ORDER_PLACED_MESSAGE = "Your Order has been successfully placed.";
    private static final String USER = "fav_user";
    private static final int PRODUCT_COUNT = 3;

    @Severity(SeverityLevel.CRITICAL)
    @Story(Constants.AllureTags.STORY_PURCHASE)
    @Test
    public void orderPlacementTest() throws InterruptedException {
        String homepageUrl = CommonSteps.getHomePageUrl();
        HomePage homePage = new HomePage(homepageUrl, webDriver);
        ElementLocatorUtil.waitUntilTitleIs(Constants.ElementLocators.HOME_PAGE_TITLE, Constants.ErrorMessages.HOME_PAGE_NOT_LOADED_ON_TIME);
        ElementLocatorUtil.waitUntilElementVanish(By.className(Constants.ElementLocators.RELOAD_SPINNER_CLASS), Constants.ErrorMessages.SPINNER_NOT_STOPPED_ON_TIME);
        Login login = homePage.getNavBarComponent().clickSignIn();
        ElementLocatorUtil.waitUntilElementAppears(By.id(Constants.ElementLocators.USER_INPUT_ID), Constants.ErrorMessages.SIGNIN_PAGE_NOT_LOADED_ON_TIME);
        login.loginWithFavUser(USER);
        ElementLocatorUtil.waitUntilElementAppears(By.className(Constants.ElementLocators.USERNAME_LABEL_CLASS), Constants.ErrorMessages.SIGNIN_NOT_COMPLETED_ON_TIME);
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