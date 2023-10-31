package com.browserstack.examples.tests.user;

import com.browserstack.examples.helpers.BaseTest;
import com.browserstack.examples.utils.UserCredentialUtil;
import com.browserstack.examples.helpers.CommonSteps;
import com.browserstack.examples.helpers.Constants;
import com.browserstack.examples.helpers.ElementLocatorUtil;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

@Feature(Constants.AllureTags.FEATURE_USER)
@Story(Constants.AllureTags.STORY_ORDER)
public class OrderTest extends BaseTest {

    private static final String EXISTING_ORDERS_ACCOUNT_USER_NAME = "existing_orders_user";

    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void existingOrdersTest() {
        CommonSteps.navigateToHome();
        signIntoExistingOrdersAccount();
        int orders = checkExistingOrders();
        Assertions.assertNotEquals(orders, 0, Constants.ErrorMessages.EMPTY_CART);
    }

    @Step("Checking orders")
    private int checkExistingOrders() {
        webDriver.findElement(By.id(Constants.ElementLocators.ORDERS_BUTTON_ID)).click();
        ElementLocatorUtil.waitUntilURLContains(Constants.EndPoints.ORDERS, Constants.ErrorMessages.ORDERS_PAGE_NOT_LOADED_ON_TIME);
        ElementLocatorUtil.waitUntilElementAppears(By.className(Constants.ElementLocators.PRODUCT_COST_CLASS), Constants.ErrorMessages.ORDERS_NOT_LOADED_ON_TIME);
        return webDriver.findElements(By.className(Constants.ElementLocators.PRODUCT_COST_CLASS)).size();
    }

    @Step("Signing in with existing_orders credentials")
    private void signIntoExistingOrdersAccount() {
        String password = UserCredentialUtil.getPassword(EXISTING_ORDERS_ACCOUNT_USER_NAME);
        CommonSteps.signIn(EXISTING_ORDERS_ACCOUNT_USER_NAME, password);
        ElementLocatorUtil.waitUntilURLContains(Constants.EndPoints.SIGNED_IN, Constants.ErrorMessages.SIGNIN_NOT_COMPLETED_ON_TIME);
    }
}