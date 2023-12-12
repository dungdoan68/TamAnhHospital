package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import pages.Lich;

public class Utils {
    public WebDriver webDriver;
    public static String TESTDATA_SHEET_PATH=System.getProperty("user.dir") + "/src/main/java/testdata";
    static Workbook book;
    static Sheet sheet;
    public String getValue(String key) throws IOException {
        Properties p = new Properties();
        FileInputStream fis = new FileInputStream(
                new File(System.getProperty("user.dir") + "/src/main/java/config/global.properties"));
        p.load(fis);
        return p.getProperty(key);
    }

    public String takeScreenShot(String testCaseName, WebDriver webDriver) throws IOException {
        TakesScreenshot tk = ((TakesScreenshot) webDriver);
        File src = tk.getScreenshotAs(OutputType.FILE);
        String fileDes = System.getProperty("user.dir") + "/src/reports/" + testCaseName + ".png";
        try{
            FileUtils.copyFile(src, new File(fileDes));
        }
        catch (IOException e){
            e.printStackTrace();
        }

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

    public void verifyLink(String url) {
        try {
            URL link = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
            httpURLConnection.setConnectTimeout(3000); // Set connection timeout to 3 seconds
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == 200) {
                System.out.println(url + " - " + httpURLConnection.getResponseMessage());
            } else {
                System.out.println(url + " - " + httpURLConnection.getResponseMessage() + " - " + "is a broken link");
            }
        } catch (Exception e) {
            System.out.println(url + " - " + "is a broken link");
        }
    }
    public void chooseEle(WebDriver webDriver,List<WebElement> listEle, String eleName) {
        for (WebElement ele : listEle) {
            try {
                if (ele.getText().equalsIgnoreCase(eleName)) {
                    System.out.println(ele.getText());
                    Thread.sleep(1000);
                    scrollToEle(webDriver,ele);
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
                System.out.println("CAN NOT CHOOSE MONTH WHICH IS LESS THAN CURRENT MONTH");
            }
        }
        int dayDiff = Integer.parseInt(date_time[0])- Calendar.getInstance().get(Calendar.DATE);
        if (date_time[0].startsWith("0")) {
                    date_time[0] = date_time[0].replace("0", "").trim();
                    System.out.println("Ngay hien tai da trim: "+date_time[0]);
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
    public Objects[][] getTestData(String testDataSheet){
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(TESTDATA_SHEET_PATH);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sheet = book.getSheet(testDataSheet);
        Objects [][] data = new Objects[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for(int i=0; i<sheet.getLastRowNum();i++){
            for(int j=0; j<sheet.getRow(0).getLastCellNum();j++){
                //data[i][j] = sheet.getRow(i+1).getCell(j).toString();
            }
        }
        return  data;
    }
    public void readExcelData(String fileName, String testDataSheet){
        FileInputStream fis = null;
        File file = new File(TESTDATA_SHEET_PATH+"/"+fileName);
        try{
            fis = new FileInputStream(file);
            System.out.println(TESTDATA_SHEET_PATH+"/"+fileName);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        if(fileExtensionName.equals("xlsx")){
            try {
                book = new XSSFWorkbook(fis);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if(fileExtensionName.equals("xls")){
            try {
                book = new HSSFWorkbook(fis);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        sheet = book.getSheet(testDataSheet);
        int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
        Objects [][] data = new Objects[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for(int i=1; i<rowCount+1;i++){
            Row row = sheet.getRow(i);
            for(int j=0; j<row.getLastCellNum();j++){
                System.out.print(row.getCell(j).getStringCellValue()+ "||");
            }
            System.out.println();
        }
    }
    public static Object[][] getTestData1(String fileName,String sheetName) {
        FileInputStream fis = null;
        File file = new File(TESTDATA_SHEET_PATH+"/"+fileName);
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
                System.out.print(data[i][k]);
            }
        }
        return data;
    }
}
