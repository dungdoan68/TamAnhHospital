package pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Menu_Practice extends Base {
    WebDriver webDriver;
    public Menu_Practice(WebDriver webDriver){
        super();
        this.webDriver =webDriver;
    }
    By cart = By.xpath("//span[@class=\"cartcontents\"]");
    By amount = By.xpath("//span[@class=\"amount\"]");
    public WebElement get_Card(){
        return webDriver.findElement(cart);
    }
    public WebElement get_Amount(){
        return webDriver.findElement(amount);
    }

}
