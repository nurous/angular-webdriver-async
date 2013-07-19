package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static helpers.By.fromTableColumn;

public class SearchResultsPage extends Page {
    public SearchResultsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String pageUrlPattern() {
        return "html/index.html";
    }

    @Override
    public String[] ajaxUrlPatterns() {
        return new String[]{"https://api.github.com/legacy/repos/search/.+"};
    }

    public List<String> getRepositories() {
        List<WebElement> elements = webDriver.findElements(fromTableColumn("Repository"));
        List<String> repositoryNames = new ArrayList<String>();
        for (WebElement element : elements) {
            repositoryNames.add(element.getText());
        }
        return repositoryNames;
    }
}
