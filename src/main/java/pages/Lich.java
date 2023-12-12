package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Lich {
    public WebDriver webDriver;
    public Lich(WebDriver webDriver){
        super();
        this.webDriver = webDriver;
    }
    By calendar = By.xpath("//body/ul/li/div/div/div/div[1]/div");
    By calendarPrev = By.xpath("//body/ul/li/div/div/div/div[1]/div[1]");
    By calendarCurrent = By.xpath("//body/ul/li/div/div/div/div[1]/div[2]");
    By calendarNext = By.xpath("//body/ul/li/div/div/div/div[1]/div[3]");
    By allDay = By.xpath("//div[@class='gia-scheduler__shifts']/div/div");
    By morning = By.xpath("//div[@class='gia-scheduler__shifts']/div/div[1]");
    By noon = By.xpath("//div[@class='gia-scheduler__shifts']/div/div[2]");
    By evening = By.xpath("//div[@class='gia-scheduler__shifts']/div/div[3]");
    By finalTime = By.xpath("//div[@class='gia-scheduler__shifts']//following-sibling::button/span/span/strong");
    By verifyButton = By.xpath("//div[@class='gia-scheduler__shifts']//following-sibling::button");
    By confirmButton = By.xpath("//div[@class='el-message-box__btns']/button");
    public WebElement calendar(){
        return webDriver.findElement(calendar);
    }
    public WebElement calendarNext(){
        return webDriver.findElement(calendarNext);
    }
    public List<WebElement> listDayOfMonth(){
        List<WebElement> a = webDriver.findElements(calendar);
        return a;
    }
    public WebElement morning(){
        return webDriver.findElement(morning);
    }
     public WebElement noon(){
        return webDriver.findElement(noon);
    }
    public WebElement evening(){
        return webDriver.findElement(evening);
    }
    public List<WebElement> allDay(){
        List<WebElement> a = webDriver.findElements(allDay);
        return a;
    }
    public WebElement chonGioKham(){
        return webDriver.findElement(finalTime);
    }
    public WebElement chonThoiGIanKham(){
        return webDriver.findElement(verifyButton);
    }
    public WebElement khongChonNgayHienTai(){
        return webDriver.findElement(confirmButton);
    }
}
