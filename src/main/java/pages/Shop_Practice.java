package pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class Shop_Practice extends Base {
    WebDriver webDriver;
    public Shop_Practice(WebDriver webDriver){
        super();
        this.webDriver =webDriver;
    }
    By cart = By.xpath("//span[@class=\"cartcontents\"]");
    By amount = By.xpath("//span[@class=\"amount\"]");
    By shop = By.xpath("//a[contains(text(),'Shop')]");
    By rightPrice = By.xpath("//div[@class=\"price_slider_wrapper\"]/div[1]/span[2]");
    By priceBar = By.xpath("//div[@class=\"price_slider_wrapper\"]/div[1]/div");
    By filter = By.xpath("//div[@class=\"price_slider_amount\"]/button");
    By orderBy = By.xpath("//select[@name=\"orderby\"]");
    public WebElement get_Card(){
        return webDriver.findElement(cart);
    }
    public WebElement get_Amount(){
        return webDriver.findElement(amount);
    }
    public WebElement get_Shop(){
        return webDriver.findElement(shop);
    }

    public WebElement get_Right_Price(){
        return webDriver.findElement(rightPrice);
    }
    public WebElement get_Price_Bar(){
        return webDriver.findElement(priceBar);
    }
    public WebElement get_Filter(){
        return webDriver.findElement(filter);
    }
    public WebElement get_OrderBy(){
        return webDriver.findElement(orderBy);
    }
    public void setAttibute(WebElement ele, String att, String value){
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])",
                ele, att, value);
    }
    public void set_Right_Bar_value(String att,String value){
        setAttibute(get_Right_Price(),att,value);
    }
    public void set_Price_Bar_value(String att,String value){
        setAttibute(get_Price_Bar(),att,value);
    }
    public void get_Product_List(){
        List<WebElement> productList = webDriver.findElements
                (By.xpath("(//div[@id=\"content\"]/ul/li/a/span/span)"));
        List<Double> price = new ArrayList<Double>();
        List<WebElement> discountProuctList = webDriver.findElements
                (By.xpath("//span[@class=\"price\"]/ins/span/span"));
        for(int i=1;i<productList.size()+1;i++){
            WebElement product_price =  webDriver.findElement
                    (By.xpath("(//div[@id=\"content\"]/ul/li/a/span/span)["+i+"]"));
            Double p = Double.parseDouble(product_price.getText().substring(1));
            price.add(p);
        }
        for(int i=1;i<discountProuctList.size()+1;i++){
            WebElement product_price =  webDriver.findElement
                    (By.xpath("(//span[@class=\"price\"]/ins/span)["+i+"]"));
            Double p = Double.parseDouble(product_price.getText().substring(1));
            price.add(p);
        }
        Double max = price.stream().mapToDouble(d->d).max().orElseThrow(NoClassDefFoundError::new);
        System.out.println("Max product price is: "+max);
        System.out.println(price.toString());
    }
}
