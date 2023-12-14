package pages;

import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Homepage_HSC {
    public  WebDriver webDriver;
    public Homepage_HSC(WebDriver webDriver){
        super();
        this.webDriver =webDriver;
    }
    @FindBy(xpath = "//table[@id=\"tableData1\"]/tbody/tr[3]/td[3]")
    WebElement giaTran;
    //@FindBy(xpath="//div[@id=\"PaginationDiv1\"]/div/div/ul/li[@title=\"Next page\"]")
    @FindBy(xpath = "(//a[contains(text(),\"»\")])[1]")
    WebElement btnNext;
    By gia = By.xpath("//table[@id=\"tableData1\"]/tbody/tr[3]/td[3]");

    public void get_ceiling_price() throws InterruptedException {
        //List<WebElement> pages = webDriver.findElements(By.xpath("//div[@id=\"PaginationDiv1\"]/div/div/ul/li"));
        double max=0;
        Utils utils = new Utils();
        List<Double> total = new ArrayList<Double>();
        List <WebElement> ceiling_prices =  webDriver.findElements(By.xpath("//table[@id=\"tableData1\"]/tbody/tr/td[3]"));;
        //for(int j=1; j<10;j++) {
            Thread.sleep(1000);
            //WebElement e = webDriver.findElement(By.xpath("//div[@id=\"PaginationDiv1\"]/div/div/ul/li["+j+"]"));
            for(int i=0;i<ceiling_prices.size();i++){
                WebElement price =webDriver.findElement(By.xpath("//table[@id=\"tableData1\"]/tbody/tr["+i+"]/td[3]"));
                utils.scrollToEle(webDriver,webDriver.findElement(By.xpath("//table[@id=\"tableData1\"]/tbody/tr["+ceiling_prices.size()+"]/td[3]")));
                total.add(Double.parseDouble(price.getText()));
            }
        //}
        max = Collections.min(total);
        System.out.println(Arrays.toString(total.stream().sorted().toArray()));
        System.out.println("MAX: "+max);
    }
}
