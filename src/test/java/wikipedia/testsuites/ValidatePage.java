package wikipedia.testsuites;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import extentReportListener.TestNGListener;
import pages.Homepage_TA;
import base.Base;

@Listeners(TestNGListener.class)
public class ValidatePage extends Base {
    @Test
    public void testBlankPage() throws IOException {
        System.out.println("tessst");
        Homepage_TA homepage = new Homepage_TA(webDriver);
//        homepage.menu().click();
//        homepage.thaoLuanChung().click();
        utils.takeScreenShot("a22bc", webDriver);
        System.out.println("ValidatePage1");
        //Assert.assertEquals(webDriver.getTitle(), "abc");
    }

    @Test
    public void testLinkWork() throws IOException {
        Homepage_TA homepage = new Homepage_TA(webDriver);
        //homepage.menu().click();
        List<WebElement> links = webDriver.findElements(By.tagName("a"));
        // Iterating each link and checking the response status
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            utils.verifyLink(url);
              System.out.println("ValidatePage2");
        }
    }

}
