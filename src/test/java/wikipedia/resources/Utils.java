package wikipedia.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utils {
    public WebDriver webDriver;
    public String getValue(String key) throws IOException{
        Properties p = new Properties();
        FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/java/wikipedia/resources/global.properties"));
        p.load(fis);
        return p.getProperty(key);
    }
    public String takeScreenShot(String testCaseName, WebDriver webDriver) throws IOException{
        TakesScreenshot tk = ((TakesScreenshot) webDriver);
        File src = tk.getScreenshotAs(OutputType.FILE);
        //String fileDes = System.getProperty("user.dir")+"/src/reports"+testCaseName+".png";
        String fileDes = System.getProperty("user.dir")+"/src/reports/"+testCaseName+".png";
        FileUtils.copyFile(src, new File(fileDes));
        return fileDes;
    }
}
