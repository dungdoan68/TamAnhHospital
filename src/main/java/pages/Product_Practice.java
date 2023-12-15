package pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Product_Practice extends Base {
    WebDriver webDriver;
    public Product_Practice(WebDriver webDriver){
        super();
        this.webDriver =webDriver;
    }
    By add_To_Cart = By.xpath("//button[@type=\"submit\"]");
    By product_Price = By.xpath("//div[@itemprop=\"offers\"]/p/ins/span");
    By viewCart = By.xpath("//div[@class=\"woocommerce-message\"]/a");
    public WebElement get_Add_To_Card(){
        return webDriver.findElement(add_To_Cart);
    }
    public WebElement get_Product_Price(){
        return webDriver.findElement(product_Price);
    }
    public Cart_Practice get_View_Cart(){
        webDriver.findElement(viewCart).click();
        Cart_Practice cart = new Cart_Practice(webDriver);
        return cart ;
    }

}
