package com.browserstack.suites.user;

import static com.browserstack.utils.Constants.AllureTags.EPIC_USER;
import static com.browserstack.utils.Constants.AllureTags.STORY_FAVOURITE_COUNT;


import com.browserstack.NonPageObjectTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic(EPIC_USER)
@Story(STORY_FAVOURITE_COUNT)
public class FavouriteCountTest extends NonPageObjectTest {

//    private static final String EXISTING_ORDERS_ACCOUNT_USER_NAME = "existing_orders_user";
//
//    @Override
//    @Step("Preprocessing capabilities")
//    public void preProcess(DesiredCapabilities desiredCapabilities) {
//        applyMask(desiredCapabilities);
//    }
//
//    @Override
//    @Step("Running the test")
//    public void run(WebDriver webDriver) {
//        navigateToHome(webDriver);
//        signIntoExistingOrdersAccount(webDriver);
//        int favourites = testFavourites(webDriver);
//        Assertions.assertNotEquals(favourites, 0, () -> markFail(FAVOURITES_COUNT));
//    }
//
//    @Override
//    @Step("Post processing")
//    public void postProcess(WebDriver webDriver) {
//        logOut(webDriver);
//    }
//
//    @Step("Checking favourites")
//    private int testFavourites(WebDriver webDriver) {
//        webDriver.findElement(By.xpath(FAVOURITE_BUTTON_XPATH)).click();
//        try {
//            WebDriverWaitUtil.getWebDriverWait(webDriver).until(waitWebDriver -> !waitWebDriver.findElements(By.xpath(FAVOURITE_BUTTON_CLICKED_XPATH)).isEmpty());
//        }
//        catch (TimeoutException e){
//            ElementLocatorUtil.waitUntilElementAppears(webDriver,this,By.xpath(FAVOURITE_BUTTON_CLICKED_XPATH),FAVOURITES_BUT_NOT_CLICKED_ON_TIME);
//        }
//        webDriver.findElement(By.id(FAVOURITES_BUTTON_ID)).click();
//        ElementLocatorUtil.waitUntilURLContains(webDriver, this, FAVOURITES, FAVOURITES_PAGE_NOT_LOADED_ON_TIME);
//        ElementLocatorUtil.waitUntilElementAppears(webDriver, this, By.className(PRODUCT_CARD_CSS), FAVOURITES_ITEMS_NOT_LOADED_ON_TIME);
//        return productCount(webDriver);
//    }
//
//    @Step("Signing in with existing_orders credentials")
//    private void signIntoExistingOrdersAccount(WebDriver webDriver) {
//        String password = UserCredentialUtil.getPassword(EXISTING_ORDERS_ACCOUNT_USER_NAME);
//        signIn(webDriver, EXISTING_ORDERS_ACCOUNT_USER_NAME, password);
//        ElementLocatorUtil.waitUntilURLContains(webDriver, this, SIGNED_IN, SIGNIN_NOT_COMPLETED_ON_TIME);
//    }
}
