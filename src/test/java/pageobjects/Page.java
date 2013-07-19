package pageobjects;

import org.openqa.selenium.WebDriver;

public abstract class Page {

    final WebDriver webDriver;

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public static <T extends Page> PageBuilder<T> create(Class<T> pageClass, WebDriver webDriver) {
        return new PageBuilder<T>(pageClass, webDriver);
    }

    protected static <T extends Page> T open(Class<T> pageClass, final String url, final WebDriver webDriver) {
        return Page.create(pageClass, webDriver).after(new Runnable() {
            @Override
            public void run() {
                webDriver.get(url);
            }
        });
    }

    public abstract String pageUrlPattern();

    public abstract String[] ajaxUrlPatterns();

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }
}
