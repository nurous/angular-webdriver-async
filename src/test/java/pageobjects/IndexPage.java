package pageobjects;

import org.openqa.selenium.WebDriver;

import static helpers.By.*;

public class IndexPage extends Page {

    private static final String FILE_PATH = Utils.getFilenameFromClasspath("html/index.html");

    public IndexPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String pageUrlPattern() {
        return "html/index.html";
    }

    @Override
    public String[] ajaxUrlPatterns() {
        return new String[0];
    }

    public static IndexPage navigateTo(final WebDriver webDriver) {
        return Page.create(IndexPage.class, webDriver).after(new Runnable() {
            @Override
            public void run() {
                webDriver.get("file://" + FILE_PATH);
            }
        });
    }

    public SearchResultsPage search(String location) {
        webDriver.findElement(label("GitHub repository search")).sendKeys(location);
        return Page.create(SearchResultsPage.class, webDriver).after(new Runnable() {
            @Override
            public void run() {
                webDriver.findElement(submitButtonText("Search")).click();
            }
        });
    }

}
