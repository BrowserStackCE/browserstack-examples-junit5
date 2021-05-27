package com.browserstack.examples.extensions;

import com.browserstack.examples.config.Platform;
import com.browserstack.examples.config.WebDriverFactory;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

public class WebDriverParameterResolver implements ParameterResolver {

    public static final ExtensionContext.Namespace STORE_NAMESPACE =
            ExtensionContext.Namespace.create("com.browserstack.examples");

    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverParameterResolver.class);

    private final WebDriverFactory webDriverFactory;
    private final Platform platform;

    public WebDriverParameterResolver(WebDriverFactory webDriverFactory, Platform platform) {
        this.webDriverFactory = webDriverFactory;
        this.platform = platform;
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(WebDriver.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) throws ParameterResolutionException {
        String testMethodName = extensionContext.getDisplayName();
        String[] specificCapabilities = new String[0];
        WebDriverTest webDriverTest = extensionContext.getTestMethod().get().getAnnotation(WebDriverTest.class);
        if (webDriverTest!=null){
            specificCapabilities = webDriverTest.capabilities();
        }
        WebDriver webDriver = createWebDriver(testMethodName,specificCapabilities);
        if (webDriver == null) {
            throw new ParameterResolutionException("Unable to create WebDriver for Platform :: "
                    + this.platform.getName() + " method :: " + testMethodName);
        }
        extensionContext.getStore(STORE_NAMESPACE).put(testMethodName + ".webDriver", webDriver);
        extensionContext.getStore(STORE_NAMESPACE).put(testMethodName + ".toMark", webDriverFactory.isToMark());
        return webDriver;
    }

    private WebDriver createWebDriver(String testMethodName, String[] specificCapabilities) {
        WebDriver webDriver;
        try {
            webDriver = this.webDriverFactory.createWebDriverForPlatform(platform, testMethodName,specificCapabilities);
        } catch (MalformedURLException malformedURLException) {
            LOGGER.error("Caught exception when creating WebDriver for Platform :: {}", platform, malformedURLException);
            throw new ParameterResolutionException(platform.toString(), malformedURLException);
        }
        return webDriver;
    }

}