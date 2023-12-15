package pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Cart_Practice extends Base {
    WebDriver webDriver;
    public Cart_Practice(WebDriver webDriver){
        super();
        this.webDriver =webDriver;
    }
    By add_To_Cart = By.xpath("//button[@type=\"submit\"]");
    By product_Price = By.xpath("//div[@itemprop=\"offers\"]/p/ins/span");
    By viewCart = By.xpath("//div[@class=\"woocommerce-message\"]/a");
    By coupon = By.xpath("//input[@name=\"coupon_code\"]");
    By applyCoupon = By.xpath("//input[@name=\"apply_coupon\"]");
    public WebElement get_Add_To_Card(){
        return webDriver.findElement(add_To_Cart);
    }
    public WebElement get_Product_Price(){
        return webDriver.findElement(product_Price);
    }
    public WebElement get_View_Cart(){
        return webDriver.findElement(viewCart);
    }
    public WebElement get_View_Coupon(){
        return webDriver.findElement(coupon);
    }
    public WebElement get_Apply_Coupon(){
        return webDriver.findElement(applyCoupon);
    }

}
