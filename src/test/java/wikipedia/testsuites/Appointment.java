package wikipedia.testsuites;

import java.time.Duration;

import extentReportListener.TestNGListener;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Homepage;
import pages.Lich;
import base.Base;
import utils.Utils;

@Listeners(TestNGListener.class)
public class Appointment extends Base {
    // public WebDriver webDriver;
    SoftAssert softAssert;
    Utils utils;

    @Test(priority = 1)
    public void appointmentSchedule() throws InterruptedException {
        softAssert = new SoftAssert();
        String date="14/12/2023";
        String time="Sáng";
        String chuyenKhoa ="Chuyên khoa Nhi";//"Chuyên khoa ngoại tiêu hóa";
        String tenBacSi="PGS.TS.BS. Nguyễn Thị Yến";
        String expectationFinalDateTime = date +" - "+time;
        utils = new Utils();
        Homepage homepage = new Homepage(webDriver);
        utils.clickElement(webDriver,homepage.haNoiBranch());
        String b = homepage.haNoiBranch().getCssValue("color");
        System.out.println("text color is: " + Color.fromString(b).asHex());// verify button clicked
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(homepage.khamTrongGio()));
        utils.clickElement(webDriver,homepage.khamTrongGio());
        wait.until(ExpectedConditions.visibilityOf(homepage.chuyenKhoa()));
        utils.scrollToEle(webDriver, homepage.tiepTheo());
        //Thread.sleep(500);
        utils.clickElement(webDriver,homepage.chuyenKhoa());
        utils.chooseEle(webDriver,homepage.listChuyenKhoa() ,chuyenKhoa);
        utils.clickElement(webDriver,homepage.bacSi());
        Thread.sleep(500);
        utils.chooseEle(webDriver,homepage.listBacSi() ,tenBacSi);
        Thread.sleep(500);
        utils.clickElement(webDriver,homepage.chonKhungGioKham());
        Thread.sleep(500);
        utils.chooseDate(webDriver,date);
        utils.chooseTime(webDriver, time);
        Lich lich = new Lich(webDriver);
        String actualFinalTime = lich.chonGioKham().getText();
        System.out.println(actualFinalTime);
        softAssert.assertEquals(actualFinalTime, expectationFinalDateTime);
        lich.chonThoiGIanKham().click();
        Actions a = new Actions(webDriver);
        a.click(homepage.vanDeSucKhoe()).perform();
        a.sendKeys(homepage.vanDeSucKhoe(), "ACBS").perform();
        homepage.tiepTheo().click();
    }
    @Test(priority = 2)
     public void selectDropdownTest() throws InterruptedException {
        softAssert = new SoftAssert();
        String date="14/12/2025";
        String time="Sáng";
        String chuyenKhoa ="Chuyên khoa Nhi";//"Chuyên khoa ngoại tiêu hóa";
        String tenBacSi="PGS.TS.BS. Nguyễn Thị Yến";
        String expectationFinalDateTime = date +" - "+time;
        utils = new Utils();
        Homepage homepage = new Homepage(webDriver);
        utils.clickElement(webDriver,homepage.haNoiBranch());
        String b = homepage.haNoiBranch().getCssValue("color");
        System.out.println("text color is: " + Color.fromString(b).asHex());// verify button clicked
        Thread.sleep(2000);
        homepage.bacSi().click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        Actions a = new Actions(webDriver);
        a.click(homepage.vanDeSucKhoe()).perform();
        a.sendKeys(homepage.vanDeSucKhoe(), "ACBS").perform();
        homepage.tiepTheo().click();
    }
}
