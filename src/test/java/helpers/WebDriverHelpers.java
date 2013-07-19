package helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.text.MessageFormat;
import java.util.List;

import static helpers.RegexMatcher.*;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class WebDriverHelpers {

    public static final String EVENTS_LOG = "window.hacks.webdriver.events";
    public static final int MAX_WAIT = 15000;
    public static final int WAIT_INTERVAL = 100;

    public static void doAndWaitForAjaxCalls(final WebDriver webDriver, Runnable runnable, final String targetUrlPattern, final String... ajaxUrlPatterns) {
        final JavascriptExecutor jsWebDriver = (JavascriptExecutor) webDriver;
        resetEventList(jsWebDriver);
        runnable.run();
        Async.waitFor(WAIT_INTERVAL, MAX_WAIT, new Runnable() {
            @Override
            public void run() {
                assertAtUrl(webDriver, targetUrlPattern);
                assertEventsFound(getEvents(jsWebDriver), "GET:{0}:finished", ajaxUrlPatterns);
            }
        });
    }

    public static void doAndWaitForAjaxError(WebDriver driver, final String ajaxUrlPattern, Runnable runnable) {
        final JavascriptExecutor jsWebDriver = (JavascriptExecutor) driver;
        resetEventList(jsWebDriver);
        runnable.run();
        Async.waitFor(WAIT_INTERVAL, MAX_WAIT, new Runnable() {
            @Override
            public void run() {
                assertEventsFound(getEvents(jsWebDriver), "(PUT|POST):{0}:failed", ajaxUrlPattern);
            }
        });
    }

    private static void assertAtUrl(WebDriver webDriver, String targetUrlPattern) {
        assertThat(webDriver.getCurrentUrl(), matches(".*" + targetUrlPattern));
    }

    private static void assertEventsFound(List<String> events, String eventFormat, String... ajaxUrlPatterns) {
        for (String ajaxUrlPattern : ajaxUrlPatterns) {
            String requestFinishedPattern = MessageFormat.format(eventFormat, ajaxUrlPattern);
//            System.out.println("events = " + events);  // Use to debug
            assertThat(events, hasItems(matches(requestFinishedPattern)));
        }
    }

    @SuppressWarnings("unchecked")
    private static List<String> getEvents(JavascriptExecutor jsWebDriver) {
        return (List<String>) jsWebDriver.executeScript("return window.hacks ? " + EVENTS_LOG + " : [];");
    }

    private static void resetEventList(JavascriptExecutor webDriver) {
        webDriver.executeScript("if (window.hacks && window.hacks.webdriver) { " + EVENTS_LOG + " = []; }");
    }

}
