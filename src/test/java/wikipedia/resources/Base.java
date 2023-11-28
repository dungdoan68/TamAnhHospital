package wikipedia.resources;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Base {
    public WebDriver webDriver;
    public  Utils utils;
    public WebDriver initialBrowsers(String browser) throws IOException{
        utils = new Utils();
        String value = utils.getValue(browser);
        if(value.equals("chrome") ){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/java/wikipedia/resources/webDrivers/chromedriver");
			webDriver = new ChromeDriver();
			
		}
		else if (value.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/test/java/wikipedia/resources/webDrivers/geckodriver");
			webDriver = new FirefoxDriver();
		}
        return webDriver;
    }
    @BeforeTest
    public void initializeDriver() throws IOException {
        webDriver = initialBrowsers("browser");
        webDriver.get(utils.getValue("baseURL"));
        webDriver.manage().window().maximize();
    }
    //@AfterTest
    public void tearDown(){
        webDriver.close();
    }

   
 
}
