package helpers;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsByXPath;

import java.util.List;

class ByLabel extends org.openqa.selenium.By {
    private final String label;

    public ByLabel(String label) {
        this.label = label;
    }

    @Override
    public List<WebElement> findElements(final SearchContext context)
    {
        String xpath = "//*[@id = //label[text() = \"" + label + "\"]/@for]";
        return ((FindsByXPath) context).findElementsByXPath(xpath);
    }

    @Override
    public WebElement findElement(final SearchContext context)
    {
        String xpath = "id(//label[text() = \"" + label + "\"]/@for)";
        return ((FindsByXPath) context).findElementByXPath(xpath);
    }

    @Override
    public String toString()
    {
        return "ByLabel: " + label;
    }
}
