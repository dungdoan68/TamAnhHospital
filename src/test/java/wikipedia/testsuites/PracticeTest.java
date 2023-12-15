package wikipedia.testsuites;

import base.Base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.Utils;

import javax.swing.*;
import java.util.*;

public class PracticeTest extends Base {
    Utils utils;
    @Test
    public void add_Product_To_Cart() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        String expectedPrice = "400.00";
        utils = new Utils();
        Homepage_Practice homepagePractice = new Homepage_Practice(webDriver);
        //Thread.sleep(2000);
        //utils.scrollToEle(webDriver,homepagePractice.getProduct());
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("const elements = document.getElementsByClassName('adsbygoogle adsbygoogle-noablate'); while (elements.length > 0) elements[0].remove()");
        utils.clickElement(webDriver,homepagePractice.getProduct(2));
        Product_Practice product = new Product_Practice(webDriver);
        product.get_Add_To_Card().click();
        Menu_Practice menu = new Menu_Practice(webDriver);
        String actualPrice = menu.get_Amount().getText();
        softAssert.assertEquals(expectedPrice,actualPrice);
    }
    @Test
    public void add_Product_To_Cart_Coupon() throws InterruptedException {
        String coupon ="krishnasakinala";
        SoftAssert softAssert = new SoftAssert();
        String expectedPrice = "400.00";
        utils = new Utils();
        Homepage_Practice homepagePractice = new Homepage_Practice(webDriver);
        Thread.sleep(2000);
        //utils.scrollToEle(webDriver,homepagePractice.getProduct());
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("const elements = document.getElementsByClassName('adsbygoogle adsbygoogle-noablate'); while (elements.length > 0) elements[0].remove()");
        //utils.clickElement(webDriver,homepagePractice.getProduct(2));
        utils.clickElement(webDriver, homepagePractice.getProduct());
        Product_Practice product = new Product_Practice(webDriver);
        product.get_Add_To_Card().click();
        Menu_Practice menu = new Menu_Practice(webDriver);
        String actualPrice = menu.get_Amount().getText();
        softAssert.assertEquals(expectedPrice,actualPrice);
        Cart_Practice cart = product.get_View_Cart();
        cart.get_View_Coupon().click();
        cart.get_View_Coupon().sendKeys(coupon);
        cart.get_Apply_Coupon().click();
    }

    @Test
    public void filter_Price() throws InterruptedException {
        String att = "style";
        String bar_value="left: 0%; width: 172%;";
        String right_price_percent = "left: 85%;";
        Menu_Practice menu = new Menu_Practice(webDriver);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("const elements = document.getElementsByClassName('adsbygoogle adsbygoogle-noablate'); " +
                "while (elements.length > 0) elements[0].remove()");
        Actions a = new Actions(webDriver);
        a.click(menu.get_Shop1());
        //a.moveToElement(menu.get_Shop1());
        a.build().perform();
        menu.get_Shop1().click();
        Shop_Practice shop = new Shop_Practice(webDriver);
        shop.set_Right_Bar_value(att,right_price_percent);
        shop.set_Price_Bar_value(att,bar_value);
        //shop.get_Price_Bar().click();
        //System.out.printf("ac: "+shop.get_Price_Bar().getAttribute(att));
        a.dragAndDrop(shop.get_Right_Price(), shop.get_Price_Bar()).build().perform();
        //a.clickAndHold(shop.get_Right_Price()).release(shop.get_Price_Bar()).build().perform();
        //a.click(shop.get_Price_Bar()).build().perform();
        shop.get_Filter().click();
        shop.get_Product_List();
    }

    @Test
    public void sorting_Products(){

    }
}
