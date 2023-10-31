package com.browserstack.examples.helpers;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    public static WebDriver webDriver;

    @BeforeEach
    public void init() {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
            webDriver = new ChromeDriver();
    }

    @AfterEach
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
