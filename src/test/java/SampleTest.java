import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobjects.IndexPage;
import pageobjects.SearchResultsPage;

import static org.hamcrest.MatcherAssert.assertThat;

public class SampleTest {
    private FirefoxDriver webDriver;

    @Test
    public void shouldWaitForAjaxCallToFinish() throws Exception {
        IndexPage indexPage = IndexPage.navigateTo(webDriver);

        SearchResultsPage searchResultsPage = indexPage.search("Angular.js");

        assertThat(searchResultsPage.getRepositories(), CoreMatchers.hasItem("angular/angular.js"));
    }

    @Before
    public void setUp() throws Exception {
        webDriver = new FirefoxDriver();
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
