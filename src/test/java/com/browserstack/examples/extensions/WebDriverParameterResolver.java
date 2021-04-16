package com.browserstack.examples.extensions;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.browserstack.examples.config.Platform;
import com.browserstack.examples.config.WebDriverFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anirudha Khanna
 */
public class WebDriverParameterResolver implements ParameterResolver {

    public static final ExtensionContext.Namespace STORE_NAMESPACE =
      ExtensionContext.Namespace.create("com.browserstack.examples");

    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverParameterResolver.class);

    private final String testMethod;
    private final WebDriverFactory webDriverFactory;
    private final Platform platform;


    public WebDriverParameterResolver(String testMethod, WebDriverFactory webDriverFactory, Platform platform) {
        this.testMethod = testMethod;
        this.webDriverFactory = webDriverFactory;
        this.platform = platform;
    }

    /**
     * Determine if this resolver supports resolution of an argument for the
     * {@link Parameter} in the supplied {@link ParameterContext} for the supplied
     * {@link ExtensionContext}.
     *
     * <p>The {@link Method} or {@link Constructor}
     * in which the parameter is declared can be retrieved via
     * {@link ParameterContext#getDeclaringExecutable()}.
     *
     * @param parameterContext the context for the parameter for which an argument should
     *                         be resolved; never {@code null}
     * @param extensionContext the extension context for the {@code Executable}
     *                         about to be invoked; never {@code null}
     * @return {@code true} if this resolver can resolve an argument for the parameter
     * @see #resolveParameter
     * @see ParameterContext
     */
    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(WebDriver.class);
    }

    /**
     * Resolve an argument for the {@link Parameter} in the supplied {@link ParameterContext}
     * for the supplied {@link ExtensionContext}.
     *
     * <p>This method is only called by the framework if {@link #supportsParameter}
     * previously returned {@code true} for the same {@link ParameterContext}
     * and {@link ExtensionContext}.
     *
     * <p>The {@link Method} or {@link Constructor}
     * in which the parameter is declared can be retrieved via
     * {@link ParameterContext#getDeclaringExecutable()}.
     *
     * @param parameterContext the context for the parameter for which an argument should
     *                         be resolved; never {@code null}
     * @param extensionContext the extension context for the {@code Executable}
     *                         about to be invoked; never {@code null}
     * @return the resolved argument for the parameter; may only be {@code null} if the
     * parameter type is not a primitive
     * @see #supportsParameter
     * @see ParameterContext
     */
    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) throws ParameterResolutionException {
        String testMethodName = extensionContext.getDisplayName();
        WebDriver webDriver = createWebDriver(testMethodName);
        extensionContext.getStore(STORE_NAMESPACE).put(testMethodName, webDriver);
        return webDriver;
    }


    private WebDriver createWebDriver(String testMethodName) {
        List<WebDriver> webDriverList = new ArrayList<>();
        WebDriver webDriver = null;
        try {
            webDriver = this.webDriverFactory.createWebDriverForPlatform(platform, testMethodName);
        } catch (MalformedURLException malformedURLException) {
            LOGGER.error("Caught exception when creating WebDriver for Platform :: {}", platform, malformedURLException);
            throw new ParameterResolutionException(platform.toString(), malformedURLException);
        }
        return webDriver;
    }

}