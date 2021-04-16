package com.browserstack.examples.extensions;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import org.junit.platform.commons.util.AnnotationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.browserstack.examples.config.Platform;
import com.browserstack.examples.config.WebDriverFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anirudha Khanna
 */
public class WebDriverTestExtension implements TestTemplateInvocationContextProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverTestExtension.class);

    /**
     * Determine if this provider supports providing invocation contexts for the
     * test template method represented by the supplied {@code context}.
     *
     * @param context the extension context for the test template method about
     *                to be invoked; never {@code null}
     * @return {@code true} if this provider can provide invocation contexts
     * @see #provideTestTemplateInvocationContexts
     * @see ExtensionContext
     */
    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        if (!context.getTestMethod().isPresent()) {
            return false;
        }

        Method testMethod = context.getTestMethod().get();
        return AnnotationUtils.isAnnotated(testMethod, WebDriverTest.class);
    }

    /**
     * Provide {@linkplain TestTemplateInvocationContext invocation contexts}
     * for the test template method represented by the supplied {@code context}.
     *
     * <p>This method is only called by the framework if {@link #supportsTestTemplate}
     * previously returned {@code true} for the same {@link ExtensionContext};
     * this method is allowed to return an empty {@code Stream} but not {@code null}.
     *
     * <p>The returned {@code Stream} will be properly closed by calling
     * {@link Stream#close()}, making it safe to use a resource such as
     * {@link Files#lines(Path) Files.lines()}.
     *
     * @param context the extension context for the test template method about
     *                to be invoked; never {@code null}
     * @return a {@code Stream} of {@code TestTemplateInvocationContext}
     * instances for the invocation of the test template method; never {@code null}
     * @see #supportsTestTemplate
     * @see ExtensionContext
     */
    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        String testMethodName = context.getRequiredTestMethod().getName();
        final List<TestTemplateInvocationContext> webDriverTestInvocationContexts = new ArrayList<>();
        try {
            final WebDriverFactory webDriverFactory = WebDriverFactory.getInstance();
            List<Platform> platforms = webDriverFactory.getPlatforms();
            platforms.forEach( p -> {
                webDriverTestInvocationContexts.add(new WebDriverTestInvocationContext(testMethodName, webDriverFactory, p));
            });
        } catch (IOException e) {
            LOGGER.error("Caught exception when getting WebDriverFactory Instance.", e);
        }

        return webDriverTestInvocationContexts.stream();
    }
}