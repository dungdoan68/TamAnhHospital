package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Homepage {
    public WebDriver webDriver;
    By menuWi = By.xpath("//header/div[1]/nav/div");
    By thaoLuanChungWi = By.xpath("//header/div[1]/nav/div/div/div/div/div[3]/div/ul/li[4]");
    By linkTitleWi = By.tagName("a");

    public WebElement menu(){
        return webDriver.findElement(menuWi);
    }
    public WebElement thaoLuanChung(){
        return webDriver.findElement(thaoLuanChungWi);
    }

    public WebElement linkTitle(){
        return webDriver.findElement(thaoLuanChungWi);
    }
    public Homepage(WebDriver webDriver){
        super();
        this.webDriver = webDriver;
    }
    By haNoiBranch= By.xpath("//span[contains(text(),'BVĐK Tâm Anh Hà Nội')]");
    By saiGonBranch =By.xpath("//span[contains(text(),'BVĐK Tâm Anh TP. HCM')]");
    By lazyLoadingTrue = By.xpath("//div[@class ='el-loading-mask' and @style='display: true;']");
    By khamTrongGio = By.xpath("//span[contains(text(),'Khám trong giờ')]");   
    By chuyenKhoa = By.xpath("(//div[@class='el-select'])[1]");
    By listChuyeKhoa = By.xpath("(//div[@class='el-select'])[1]/div/div[1]/div/ul/li");
    By bacSi = By.xpath("(//div[@class='el-select'])[2]");
    By listBacSi = By.xpath("(//div[@class='el-select'])[2]/div[2]/div[1]/div/ul/li");
    By tiepTheo = By.xpath("//span[contains(text(),'TIẾP THEO →')]");
    By vanDeSucKhoe = By.xpath("//label[contains(text(),'Nhập vấn đề ')]//following-sibling::div");

    By chonKhungGioKham = By.xpath("(//div[@class='el-form-item__content'])[5]");
   
    public WebElement haNoiBranch(){
        return webDriver.findElement(haNoiBranch);
    }
    public WebElement saiGonBranch(){
        return webDriver.findElement(saiGonBranch);
    }
    public WebElement lazyLoadingTrue(){
        return webDriver.findElement(lazyLoadingTrue);
    }
    public WebElement khamTrongGio(){
        return webDriver.findElement(khamTrongGio);
    }
    public WebElement chuyenKhoa(){
        return webDriver.findElement(chuyenKhoa);
    }
      public WebElement bacSi(){
        return webDriver.findElement(bacSi);
    }
    public WebElement tiepTheo(){
        return webDriver.findElement(tiepTheo);
    }
    public WebElement vanDeSucKhoe(){
        return webDriver.findElement(vanDeSucKhoe);
    }
    
    public List<WebElement> listChuyenKhoa(){
        List<WebElement> a = webDriver.findElements(listChuyeKhoa);
        return a;
    }
    public List<WebElement> listBacSi(){
        List<WebElement> a = webDriver.findElements(listBacSi);
        return a;
    }
    public WebElement chonKhungGioKham(){
        return webDriver.findElement(chonKhungGioKham);
    }
   
}