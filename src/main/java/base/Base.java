package base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import utils.Utils;

public class Base {

    public WebDriver webDriver;
    public Utils utils;
    public WebDriver initialBrowsers(String browser) throws IOException{
        utils = new Utils();
        String value = utils.getValue(browser);
        if(value.equals("chrome") ){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/java/resources/chromedriver");
			webDriver = new ChromeDriver();
			
		}
		else if (value.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/main/java/resources/geckodriver");
			webDriver = new FirefoxDriver();
		}
        return webDriver;
    }
    @BeforeTest
    public void initializeDriver() throws IOException {
        System.out.println("before Test");
        webDriver = initialBrowsers("browser");
        webDriver.get(utils.getValue("baseURL"));
        webDriver.manage().window().maximize();
    }
    @AfterTest
    public void tearDown(){
        System.out.println("after test");
        webDriver.close();
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("before class");
    }
    @AfterClass
    public void afterCalss(){
        System.out.println("after class");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("before Method");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("after Method");
    }
 
}
