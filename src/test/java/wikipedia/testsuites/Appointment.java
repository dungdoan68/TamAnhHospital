package wikipedia.testsuites;

import java.text.ParseException;
import java.time.Duration;

import extentReportListener.TestNGListener;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Homepage_TA;
import pages.Lich;
import base.Base;
import utils.Utils;

@Listeners(TestNGListener.class)
public class Appointment extends Base {
    // public WebDriver webDriver;
    SoftAssert softAssert;
    Utils utils;

    @Test(priority = 2)
    public void appointmentSchedule() throws InterruptedException, ParseException {
        softAssert = new SoftAssert();
        utils = new Utils();
        String date=utils.getNextDay();
        String time="Sáng";
        String chuyenKhoa ="Chuyên khoa Nhi";//"Chuyên khoa ngoại tiêu hóa";
        String tenBacSi="PGS.TS.BS. Nguyễn Thị Yến";
        String expectationFinalDateTime = date +" - "+time;
        Homepage_TA homepage = new Homepage_TA(webDriver);
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
    @DataProvider(name = "chuyen khoa")
    public Object[][] chon_Chuyen_Khoa() {
        //return new Object[][] {{2, 3 , 5}, {5, 7, 9}};
        Object data[][] =Utils.getTestData1("tam anh.xlsx","Sheet1");
        return  data;
    }
    @Test(priority = 2,dataProvider ="chuyen khoa")
     public void testDataProvider(String chuyenKhoa, String bacSi) throws InterruptedException, ParseException {
        softAssert = new SoftAssert();
        utils = new Utils();
        String date=utils.getNextDay();
        String time="Sáng";
        String expectationFinalDateTime = date +" - "+time;
        Homepage_TA homepage = new Homepage_TA(webDriver);
        utils.clickElement(webDriver,homepage.haNoiBranch());
        String b = homepage.haNoiBranch().getCssValue("color");
        System.out.println("text color is: " + Color.fromString(b).asHex());// verify button clicked
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(homepage.khamTrongGio()));
        utils.clickElement(webDriver,homepage.khamTrongGio());
        wait.until(ExpectedConditions.visibilityOf(homepage.chuyenKhoa()));
        utils.scrollToEle(webDriver, homepage.tiepTheo());
        utils.clickElement(webDriver,homepage.chuyenKhoa());
        utils.chooseEle(webDriver,homepage.listChuyenKhoa() ,chuyenKhoa);
        utils.clickElement(webDriver,homepage.bacSi());
        Thread.sleep(500);
        utils.chooseEle(webDriver,homepage.listBacSi() ,bacSi);
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
}
