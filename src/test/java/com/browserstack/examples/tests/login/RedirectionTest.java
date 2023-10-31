package com.browserstack.examples.tests.login;

import com.browserstack.examples.helpers.BaseTest;
import com.browserstack.examples.helpers.ElementLocatorUtil;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.browserstack.examples.helpers.CommonSteps.navigateToHome;
import static com.browserstack.examples.helpers.Constants.AllureTags.FEATURE_LOGIN;
import static com.browserstack.examples.helpers.Constants.AllureTags.STORY_REDIRECTION;
import static com.browserstack.examples.helpers.Constants.ElementLocators.FAVOURITES_BUTTON_ID;
import static com.browserstack.examples.helpers.Constants.EndPoints.SIGNED_IN_FAVOURITES;
import static com.browserstack.examples.helpers.Constants.EndPoints.SIGN_IN;
import static com.browserstack.examples.helpers.Constants.ErrorMessages.CLICK_FAVOURITES;
import static com.browserstack.examples.helpers.Constants.ErrorMessages.SIGNIN_PAGE_NOT_LOADED_ON_TIME;

@Feature(FEATURE_LOGIN)
@Story(STORY_REDIRECTION)
public class RedirectionTest extends BaseTest {

    @Severity(SeverityLevel.MINOR)
    @Test
    public void favouritesRedirectsLoginTest() {
        navigateToHome();
        clickOnFavourite();
        checkRedirect();
        Assertions.assertNotEquals(-1, webDriver.getCurrentUrl().indexOf(SIGNED_IN_FAVOURITES), CLICK_FAVOURITES);
    }

    @Step("Clicking favourites to see if it navigates to sign in page")
    private void clickOnFavourite() {
        WebElement favouritesOption = webDriver.findElement(By.id(FAVOURITES_BUTTON_ID));
        favouritesOption.click();
    }

    @Step("Checking if it redirects to the sign in page")
    private void checkRedirect() {
        ElementLocatorUtil.waitUntilURLContains(SIGN_IN, SIGNIN_PAGE_NOT_LOADED_ON_TIME);
    }

}