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
import org.openqa.selenium.WebElement;

import java.util.List;

@Feature(Constants.AllureTags.FEATURE_USER)
@Story(Constants.AllureTags.STORY_IMAGE)
public class ImageTest extends BaseTest {

    private static final String IMAGE_NOT_LOADING_ACCOUNT_USER_NAME = "image_not_loading_user";

    @Severity(SeverityLevel.MINOR)
    @Test
    public void imageLoadingTest() {
        CommonSteps.navigateToHome();
        signIntoImageNotLoadingAccount();
        List<WebElement> images = webDriver.findElements(By.tagName(Constants.ElementLocators.PRODUCT_IMAGE_TAG));
        images.forEach(image ->
                Assertions.assertTrue(image.getAttribute(Constants.ElementLocators.PRODUCT_IMAGE_SOURCE_ATTRIBUTE).isEmpty(), Constants.ErrorMessages.IMAGE_NOT_LOADING));
    }

    @Step("Signing in with image_not_loading credentials")
    private void signIntoImageNotLoadingAccount() {
        String password = UserCredentialUtil.getPassword(IMAGE_NOT_LOADING_ACCOUNT_USER_NAME);
        CommonSteps.signIn(IMAGE_NOT_LOADING_ACCOUNT_USER_NAME, password);
        ElementLocatorUtil.waitUntilURLContains(Constants.EndPoints.SIGNED_IN, Constants.ErrorMessages.SIGNIN_NOT_COMPLETED_ON_TIME);
    }
}