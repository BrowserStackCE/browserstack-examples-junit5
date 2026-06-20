package com.browserstack.examples.tests.user;

import com.browserstack.examples.helpers.BaseTest;
import com.browserstack.examples.utils.UserCredentialUtil;
import com.browserstack.examples.utils.WebDriverWaitUtil;
import com.browserstack.examples.helpers.ElementLocatorUtil;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import static com.browserstack.examples.helpers.CommonSteps.*;
import static com.browserstack.examples.helpers.Constants.AllureTags.FEATURE_USER;
import static com.browserstack.examples.helpers.Constants.AllureTags.STORY_FAVOURITES;
import static com.browserstack.examples.helpers.Constants.ElementLocators.*;
import static com.browserstack.examples.helpers.Constants.EndPoints.FAVOURITES;
import static com.browserstack.examples.helpers.Constants.EndPoints.SIGNED_IN;
import static com.browserstack.examples.helpers.Constants.ErrorMessages.*;

@Feature(FEATURE_USER)
@Story(STORY_FAVOURITES)
public class FavouritesTest extends BaseTest {

    private static final String EXISTING_ORDERS_ACCOUNT_USER_NAME = "existing_orders_user";

    @Severity(SeverityLevel.TRIVIAL)
    @Test
    public void favouritesCountTest() {
        navigateToHome();
        signIntoExistingOrdersAccount();
        int favourites = testFavourites();
        Assertions.assertNotEquals(favourites, 0, FAVOURITES_COUNT);
    }

    @Step("Checking favourites")
    private int testFavourites() {
        webDriver.findElement(By.className(FAVOURITE_BUTTON_CLASS)).click();
        try {
            WebDriverWaitUtil.getWebDriverWait(webDriver).until(waitWebDriver -> !waitWebDriver.findElements(By.className(FAVOURITE_BUTTON_CLICKED_CLASS)).isEmpty());
        } catch (TimeoutException e) {
            ElementLocatorUtil.waitUntilElementAppears(By.className(FAVOURITE_BUTTON_CLICKED_CLASS), FAVOURITES_BUT_NOT_CLICKED_ON_TIME);
        }
        webDriver.findElement(By.id(FAVOURITES_BUTTON_ID)).click();
        ElementLocatorUtil.waitUntilURLContains(FAVOURITES, FAVOURITES_PAGE_NOT_LOADED_ON_TIME);
        ElementLocatorUtil.waitUntilElementAppears(By.className(PRODUCT_CARD_CSS), FAVOURITES_ITEMS_NOT_LOADED_ON_TIME);
        return productCount();
    }

    @Step("Signing in with existing_orders credentials")
    private void signIntoExistingOrdersAccount() {
        String password = UserCredentialUtil.getPassword(EXISTING_ORDERS_ACCOUNT_USER_NAME);
        signIn(EXISTING_ORDERS_ACCOUNT_USER_NAME, password);
        ElementLocatorUtil.waitUntilURLContains(SIGNED_IN, SIGNIN_NOT_COMPLETED_ON_TIME);
    }
}