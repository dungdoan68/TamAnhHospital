package wikipedia.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Homepage {
    public WebDriver webDriver;
    By menu = By.xpath("//header/div[1]/nav/div");
    By thaoLuanChung = By.xpath("//header/div[1]/nav/div/div/div/div/div[3]/div/ul/li[4]");
    By linkTitle = By.tagName("a");

    public WebElement menu(){
        return webDriver.findElement(menu);
    }
    public WebElement thaoLuanChung(){
        return webDriver.findElement(thaoLuanChung);
    }

    public WebElement linkTitle(){
        return webDriver.findElement(linkTitle);
    }
    public Homepage(WebDriver webDriver){
        super();
        this.webDriver = webDriver;
    }
}
