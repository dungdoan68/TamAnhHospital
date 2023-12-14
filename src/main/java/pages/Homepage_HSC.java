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
}
