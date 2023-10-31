package com.browserstack.examples.tests.product;

import com.browserstack.examples.helpers.BaseTest;
import com.browserstack.examples.helpers.ProductUtil;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Collections;

import static com.browserstack.examples.helpers.CommonSteps.*;
import static com.browserstack.examples.helpers.Constants.AllureTags.FEATURE_PRODUCT;
import static com.browserstack.examples.helpers.Constants.AllureTags.STORY_APPLY_BRAND_FILTER;
import static com.browserstack.examples.helpers.Constants.ElementLocators.APPLE_FILTER_XPATH;
import static com.browserstack.examples.helpers.Constants.ElementLocators.SAMSUNG_FILTER_XPATH;
import static com.browserstack.examples.helpers.Constants.ErrorMessages.APPLY_BRAND_FILTER;
import static com.browserstack.examples.helpers.Constants.ErrorMessages.PRODUCT_COUNT_MISMATCH;

@Feature(FEATURE_PRODUCT)
@Story(STORY_APPLY_BRAND_FILTER)
public class BrandFilterTest extends BaseTest {

    private static final String BRAND_APPLE = "apple";
    private static final String BRAND_SAMSUNG = "samsung";

    @Severity(SeverityLevel.NORMAL)
    @Test
    public void appleFilterTest() {
        navigateToHome();
        int totalCount = productCount();
        applyBrandFilter(APPLE_FILTER_XPATH);
        int brandCount = productCount();
        verifyCount(BRAND_APPLE, totalCount, brandCount);
    }

    @Test
    public void samsungFilterTest() {
        navigateToHome();
        int totalCount = productCount();
        applyBrandFilter(SAMSUNG_FILTER_XPATH);
        int brandCount = productCount();
        verifyCount(BRAND_SAMSUNG, totalCount, brandCount);
    }

    @Step("Applying brand filter")
    private void applyBrandFilter(String brandXPath) {
        WebElement brandFilter = webDriver.findElement(By.xpath(brandXPath));
        brandFilter.click();
        waitForSpinner();
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