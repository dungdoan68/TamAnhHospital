package wikipedia.testsuites;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.Homepage_HSC;
import utils.Utils;

import java.util.*;

public class GetPriceTest extends Base {
    @Test
    public void getMax_Ceiling_Price() throws InterruptedException {
        Homepage_HSC homepageHsc = new Homepage_HSC(webDriver);
        Thread.sleep(1000);
        double max=0;
        Utils utils = new Utils();
        List<Double> total = new ArrayList<Double>();
        List <WebElement> ceiling_prices =  webDriver.findElements(By.xpath("//table[@id=\"tableData1\"]/tbody/tr/td[3]"));;
        for(int i=1;i<ceiling_prices.size();i++){
            WebElement price =webDriver.findElement(By.xpath("//table[@id=\"tableData1\"]/tbody/tr["+i+"]/td[3]"));
            utils.scrollToEle(webDriver,webDriver.findElement(By.xpath("//table[@id=\"tableData1\"]/tbody/tr["+ceiling_prices.size()+"]/td[3]")));
            total.add(Double.parseDouble(price.getText()));
        }
        Double a = total.stream().mapToDouble(d->d).max().orElseThrow(NoSuchElementException::new);
        max = Collections.max(total);
        System.out.println(Arrays.toString(total.stream().sorted().toArray()));
        System.out.println("MAX: "+max);
    }

}
