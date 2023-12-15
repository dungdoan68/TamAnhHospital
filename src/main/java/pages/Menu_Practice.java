package pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Menu_Practice extends Base {
    WebDriver webDriver;
    public Menu_Practice(WebDriver webDriver){
        super();
        this.webDriver =webDriver;
    }
    By cart = By.xpath("//span[@class=\"cartcontents\"]");
    By amount = By.xpath("//span[@class=\"amount\"]");
    By shop = By.xpath("//nav/ul/li[1]");
    public WebElement get_Card(){
        return webDriver.findElement(cart);
    }
    public WebElement get_Amount(){
        return webDriver.findElement(amount);
    }
    public Shop_Practice get_Shop(){
        Actions a = new Actions(webDriver);
        a.click(get_Shop1()).build().perform();
        Shop_Practice s = new Shop_Practice(webDriver);
        return s;
    }
    public WebElement get_Shop1(){
        return webDriver.findElement(shop);
    }

}
