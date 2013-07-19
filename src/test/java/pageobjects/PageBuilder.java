package pageobjects;

import helpers.WebDriverHelpers;
import org.openqa.selenium.WebDriver;

public class PageBuilder<T extends Page> {
    private final Class<T> pageClass;
    private final WebDriver webDriver;

    public PageBuilder(Class<T> pageClass, WebDriver webDriver) {
        this.pageClass = pageClass;
        this.webDriver = webDriver;
    }

    T after(Runnable closure) {
        T instance = create();
        WebDriverHelpers.doAndWaitForAjaxCalls(webDriver, closure, instance.pageUrlPattern(), instance.ajaxUrlPatterns());
        return instance;
    }

    private T create() {
        try {
            return pageClass.getConstructor(WebDriver.class).newInstance(webDriver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
