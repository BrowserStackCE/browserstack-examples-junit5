package com.browserstack.examples.tests.product;

import com.browserstack.examples.helpers.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.browserstack.examples.helpers.CommonSteps.*;
import static com.browserstack.examples.helpers.Constants.AllureTags.FEATURE_PRODUCT;
import static com.browserstack.examples.helpers.Constants.AllureTags.STORY_ORDER_BY_FILTER;
import static com.browserstack.examples.helpers.Constants.ElementLocators.*;
import static com.browserstack.examples.helpers.Constants.ErrorMessages.ORDER_BY_FILTER;

@Feature(FEATURE_PRODUCT)
@Story(STORY_ORDER_BY_FILTER)
public class OrderByFilterTest extends BaseTest {

    @Severity(SeverityLevel.TRIVIAL)
    @Test
    public void lowestToHighestFilterTest() {
        navigateToHome();
        orderByLowestToHighest();
        int productCount = productCount();
        int lastCost = findCostByIndex(0), currentCost;
        for (int i = 1; i < productCount; i++) {
            currentCost = findCostByIndex(i);
            checkingCostOrder(currentCost, lastCost);
            lastCost = currentCost;
        }
    }

    @Step("Applying Order by Lowest to Highest Filter")
    private void orderByLowestToHighest() {
        WebElement orderByDropDown = webDriver.findElement(By.cssSelector(ORDER_BY_DROP_DOWN_CSS));
        WebElement lowestToHighestOption = orderByDropDown.findElement(By.xpath(LOWEST_TO_HIGHEST_DROP_DOWN_OPTION_BY_XPATH));
        lowestToHighestOption.click();
        waitForSpinner();
    }

    @Step("Finding product at index {1}")
    private WebElement findProductByIndex(int index) {
        return webDriver.findElements(By.className(PRODUCT_CARD_CSS)).get(index);
    }

    @Step("Finding cost of product at index {1}")
    private int findCostByIndex(int index) {
        WebElement product = findProductByIndex(index);
        String cost = product
                .findElement(By.className(PRODUCT_PRICE_CARD_CLASS))
                .findElement(By.className(PRODUCT_PRICE_VALUE_CARD_CLASS))
                .findElement(By.tagName(PRODUCT_COST_BOLD_TAG)).getText();
        return Integer.parseInt(cost);
    }

    @Step("Verifying current cost [{0}] >= previous cost [{1}]")
    private void checkingCostOrder(int currentCost, int lastCost) {
        Assertions.assertTrue(currentCost >= lastCost, ORDER_BY_FILTER);
    }

}