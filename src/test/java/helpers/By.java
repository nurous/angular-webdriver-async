package helpers;

import static java.lang.String.format;
import static org.openqa.selenium.By.cssSelector;

public class By {
    public static org.openqa.selenium.By label(final String label) {
        return new ByLabel(label);
    }

    public static org.openqa.selenium.By submitButtonText(String text) {
        return cssSelector(format("input[type='submit'][value='%s']", text));
    }

    public static org.openqa.selenium.By fromTableColumn(String columnName) {
        return org.openqa.selenium.By.xpath("//table/tbody/tr/td[" +
                "count(preceding-sibling::td)+1" +
                " = " +
                "count(ancestor::table/thead/tr/th[.='" + columnName + "']/preceding-sibling::th)+1" +
                "]");
    }
}
