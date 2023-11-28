package wikipedia.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import wikipedia.pageObjects.Homepage;
import wikipedia.pageObjects.Lich;

public class Utils {
    public WebDriver webDriver;

    public String getValue(String key) throws IOException {
        Properties p = new Properties();
        FileInputStream fis = new FileInputStream(
                new File(System.getProperty("user.dir") + "/src/test/java/wikipedia/resources/global.properties"));
        p.load(fis);
        return p.getProperty(key);
    }

    public String takeScreenShot(String testCaseName, WebDriver webDriver) throws IOException {
        TakesScreenshot tk = ((TakesScreenshot) webDriver);
        File src = tk.getScreenshotAs(OutputType.FILE);
        // String fileDes =
        // System.getProperty("user.dir")+"/src/reports"+testCaseName+".png";
        String fileDes = System.getProperty("user.dir") + "/src/reports/" + testCaseName + ".png";
        FileUtils.copyFile(src, new File(fileDes));
        return fileDes;
    }

    public void clickElement(WebDriver webDriver, WebElement ele) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofMillis(3000));
            wait.until(ExpectedConditions.visibilityOf(ele));
            ele.click();

        } catch (Exception e) {
            System.out.println("Can not click at: " + ele.getText());
        }

    }

    public void scrollToEle(WebDriver webDriver, WebElement ele) {
        // ((JavascriptExecutor)
        // webDriver).executeScript("arguments[0].scrollIntoView(true);", ele);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(ele);
        actions.perform();
    }

    public void chooseEle(List<WebElement> listEle, String eleName) {
        for (WebElement ele : listEle) {
            try {
                if (ele.getText().equalsIgnoreCase(eleName) == true) {
                    System.out.println(ele.getText());
                    ele.click();
                    break;
                }
            } catch (Exception e) {
                System.out.println("No info choosen");
            }

        }
    }

    // Date time format dd/mm/yyyy
    public void chooseDate(WebDriver webDriver, String dateTime) {
        Lich lich = new Lich(webDriver);
        String date_time[] = dateTime.split("/");
        int yearDiff = Integer.parseInt(date_time[2]) - Calendar.getInstance().get(Calendar.YEAR);
        if (yearDiff != 0) {
            if (yearDiff > 0) {
                for (int i = 0; i < yearDiff; i++) {
                    lich.calendarNext().click();
                }
            }
        }
        int monthDiff = Integer.parseInt(date_time[1]) - Calendar.getInstance().get(Calendar.MONTH);
        if (monthDiff != 0) {
            if (monthDiff > 0) {
                for (int i = 0; i < monthDiff - 1; i++) {
                    lich.calendarNext().click();
                    break;
                }
            }
            if (monthDiff < 0) {
                System.out.println("CAN NOT CHOOSE MONTH WWHICH IS LESS THAN CURRENT MONTH");
            }
        }
        int dayDiff = Integer.parseInt(date_time[0])- Calendar.getInstance().get(Calendar.DATE);
        if (date_time[0].startsWith("0")) {
                    date_time[0] = date_time[0].replace("0", "").trim();
                    System.out.println("Ngay hien tai da trim"+date_time[0]);
                }
        if(dayDiff!=0){
            System.out.println("Ngay hien tai"+date_time[0]);
            for (WebElement e : lich.listDayOfMonth()) {
                if (e.getText().equalsIgnoreCase(date_time[0])) {
                    if (e.getAttribute("class").contentEquals("gia-scheduler__date gia-scheduler__date--disabled")) {
                        System.out.println("YOU CAN NOT PICK DAY: " + e.getText());
                        break;
                    } else {
                        System.out.println("Day for appointment is: " + e.getText());
                        e.click();
                        System.out.println(e.getCssValue("background"));
                        break;
                    }
                }
            }
        }else{
            for (WebElement e : lich.listDayOfMonth()) {
                if (e.getText().equalsIgnoreCase(date_time[0])) {
                    if (e.getAttribute("class").contentEquals("gia-scheduler__date gia-scheduler__date--disabled")) {
                        System.out.println("YOU CAN NOT PICK DAY: " + e.getText());
                        break;
                    } else {
                        System.out.println("Day for appointment is: " + e.getText());
                        System.out.println("Can NOT CHOOSE CURRENT DAY");
                        e.click();
                        lich.khongChonNgayHienTai().click();
                        break;
                    }
                }
            }  
        }
    }
    public void chooseTime(WebDriver webDriver,String time){
        Lich lich = new Lich(webDriver);
         for (WebElement e : lich.allDay()) {
            if (e.getText().equalsIgnoreCase(time)) {
                if (e.getAttribute("class").contentEquals("eco-el-radio-button unactive-eco-radio disable-eco-radio")) {
                    System.out.println("YOU CAN NOT PICK TIME: " + e.getText());
                    break;
                } else {
                    System.out.println("Day for appointment is: " + e.getText());
                    e.click();
                    System.out.println(e.getCssValue("background"));
                    break;
                }
            }
        }
    }
}
