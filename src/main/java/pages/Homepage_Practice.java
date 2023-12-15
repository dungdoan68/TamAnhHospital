package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Homepage_Practice {
    WebDriver webDriver;
    public Homepage_Practice(WebDriver webDriver){
        super();
        this.webDriver =webDriver;
    }
    By product = By.xpath( "(//ul[@class=\"products\"])[2]");
    public WebElement getProduct(){
        return webDriver.findElement(product);
    }

    By newArrival = By.xpath ("//h2[contains(text(),'new arrivals')]");
    public WebElement getNewArrival(){
        return  webDriver.findElement(newArrival);
    }
    public WebElement getProduct(int index){
        WebElement p=null;
        List<WebElement> product_list = webDriver.findElements(By.xpath("(//ul[@class=\"products\"])"));
        for(int i=1;i<product_list.size()+1;i++){
            if(i==index){
                p = webDriver.findElement(By.xpath("(//ul[@class=\"products\"])["+i+"]"));
                break;
            }
        }
        return p;
    }

}
