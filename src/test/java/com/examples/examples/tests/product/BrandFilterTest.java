package com.examples.examples.tests.product;

import com.examples.examples.extensions.WebDriverTest;
import com.examples.examples.helpers.ProductUtil;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collections;

import static com.examples.examples.helpers.CommonSteps.*;
import static com.examples.examples.helpers.Constants.AllureTags.EPIC_PRODUCT;
import static com.examples.examples.helpers.Constants.AllureTags.STORY_APPLY_BRAND_FILTER;
import static com.examples.examples.helpers.Constants.ElementLocators.APPLE_FILTER_XPATH;
import static com.examples.examples.helpers.Constants.ElementLocators.SAMSUNG_FILTER_XPATH;
import static com.examples.examples.helpers.Constants.ErrorMessages.APPLY_BRAND_FILTER;
import static com.examples.examples.helpers.Constants.ErrorMessages.PRODUCT_COUNT_MISMATCH;

@Epic(EPIC_PRODUCT)
@Story(STORY_APPLY_BRAND_FILTER)
public class BrandFilterTest {

    private static final String BRAND_APPLE = "apple";
    private static final String BRAND_SAMSUNG = "samsung";

    @WebDriverTest
    public void appleFilterTest(WebDriver webDriver) {
        navigateToHome(webDriver);
        int totalCount = productCount(webDriver);
        applyBrandFilter(APPLE_FILTER_XPATH, webDriver);
        int brandCount = productCount(webDriver);
        verifyCount(BRAND_APPLE, totalCount, brandCount);
    }

    @WebDriverTest
    public void samsungFilterTest(WebDriver webDriver) {
        navigateToHome(webDriver);
        int totalCount = productCount(webDriver);
        applyBrandFilter(SAMSUNG_FILTER_XPATH, webDriver);
        int brandCount = productCount(webDriver);
        verifyCount(BRAND_SAMSUNG, totalCount, brandCount);
    }

    @Step("Applying brand filter")
    private void applyBrandFilter(String brandXPath, WebDriver webDriver) {
        WebElement brandFilter = webDriver.findElement(By.xpath(brandXPath));
        brandFilter.click();
        waitForSpinner(webDriver);
    }

    @Step("Verifying the product counts for brand : {0}")
    private void verifyCount(String brand, int totalCount, int brandCount) {
        int expectedTotalCount = ProductUtil.getAllProducts().size();
        int expectedBrandCount = ProductUtil.getProductsByBrands(Collections.singletonList(brand)).size();
        Assertions.assertEquals(expectedTotalCount, totalCount, PRODUCT_COUNT_MISMATCH);
        Assertions.assertEquals(expectedBrandCount, brandCount, PRODUCT_COUNT_MISMATCH);
        Assertions.assertTrue(totalCount > brandCount, APPLY_BRAND_FILTER);
    }
}