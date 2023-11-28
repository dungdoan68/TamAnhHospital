package wikipedia.testsuites;

import java.time.Duration;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import wikipedia.Listener;
import wikipedia.pageObjects.Homepage;
import wikipedia.pageObjects.Lich;
import wikipedia.resources.Base;
import wikipedia.resources.Utils;

@Listeners(Listener.class)
public class Appointment extends Base {
    // public WebDriver webDriver;
    SoftAssert softAssert;
    Utils utils;

    @Test
    public void appointmentSchedule() throws InterruptedException {
        softAssert = new SoftAssert();
        String date="29/11/2023";
        String time="Sáng";
        String chuyenKhoa ="Chuyên khoa Nhi";
        String tenBacSi="TS.BS. Lương Thị Thu Hiền";
        String expectationFinalDateTime = date +" - "+time;
        utils = new Utils();
        Thread.sleep(2000);
        Homepage homepage = new Homepage(webDriver);
       
        utils.clickElement(webDriver,homepage.haNoiBranch());
        String b = homepage.haNoiBranch().getCssValue("color");
        System.out.println("text color is: " + Color.fromString(b).asHex());// verify button clicked
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        try {
            wait.until(ExpectedConditions.invisibilityOf(homepage.lazyLoadingTrue()));
            System.out.println("LAZY LOADING");

        } catch (Exception e) {
            System.out.println("NO LAZY LOADING");
        }
        wait.until(ExpectedConditions.visibilityOf(homepage.khamTrongGio()));
        utils.clickElement(webDriver,homepage.khamTrongGio());
        wait.until(ExpectedConditions.visibilityOf(homepage.chuyenKhoa()));
        utils.scrollToEle(webDriver, homepage.tiepTheo());
        Thread.sleep(500);
        utils.clickElement(webDriver,homepage.chuyenKhoa());
        utils.chooseEle(homepage.listChuyenKhoa() ,chuyenKhoa);
        utils.clickElement(webDriver,homepage.bacSi());
        Thread.sleep(500);
        utils.chooseEle(homepage.listBacSi() ,tenBacSi);
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
