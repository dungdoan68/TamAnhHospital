package wikipedia.testsuites;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.Utils;

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

}
