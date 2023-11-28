package wikipedia.testsuites;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import wikipedia.Listener;
import wikipedia.pageObjects.Homepage;
import wikipedia.resources.Base;

@Listeners(Listener.class)
public class ValidatePage extends Base {
    public WebDriver webDriver;

    @BeforeTest
    public void initializeDriver() throws IOException {
        webDriver = initialBrowsers("browser");
        webDriver.get(utils.getValue("baseURL"));
        webDriver.manage().window().maximize();
    }
    @Test
    public void testBlankPage() throws IOException {
        System.out.println("tessst");
        Homepage homepage = new Homepage(webDriver);
        homepage.menu().click();
        homepage.thaoLuanChung().click();
        Assert.assertEquals(webDriver.getTitle(), "abc");
        utils.takeScreenShot("a22bc", webDriver);
    }

    @Test
    public void testLinkWork() throws IOException {
        Homepage homepage = new Homepage(webDriver);
        homepage.menu().click();
       
        List<WebElement> links = webDriver.findElements(By.tagName("a"));

        // Iterating each link and checking the response status
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            verifyLink(url);
        }

    }

    public static void verifyLink(String url) {
        try {
            URL link = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
            httpURLConnection.setConnectTimeout(3000); // Set connection timeout to 3 seconds
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == 200) {
                System.out.println(url + " - " + httpURLConnection.getResponseMessage());
            } else {
                System.out.println(url + " - " + httpURLConnection.getResponseMessage() + " - " + "is a broken link");
            }
        } catch (Exception e) {
            System.out.println(url + " - " + "is a broken link");
        }
    }

    @AfterTest
    public void tearDown() {
        webDriver.close();
    }
}
