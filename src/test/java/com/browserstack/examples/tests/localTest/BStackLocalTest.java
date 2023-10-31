package com.browserstack.examples.tests.localTest;


import com.browserstack.examples.helpers.BaseTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class BStackLocalTest extends BaseTest {
    @Test
    public void test() {
        webDriver.get("http://localhost:45454/");
        assertTrue(webDriver.getTitle().contains("BrowserStack Local"), "Local content not validated!");
    }
}

