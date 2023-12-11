package wikipedia;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import wikipedia.resources.Base;
import wikipedia.resources.Utils;

public class Listener implements ITestListener {
    ExtentReports extent = Reports.getReportObject();
	ExtentTest extentTest;
	ThreadLocal<ExtentTest> testLocal = new ThreadLocal<ExtentTest>();//give pool contain variable "extentTest" to define what test is running
	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extent.createTest(result.getMethod().getMethodName());
		testLocal.set(extentTest);//set the present running test to the pool
		System.out.println(result.getMethod().getMethodName()+ "Start test");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Utils utils = new Utils();
		testLocal.get().log(Status.PASS, result.getMethod().getMethodName() + " pass");
		WebDriver webDriver = null;
		String testCaseName = result.getMethod().getMethodName();
		try {
			webDriver = (WebDriver)result.getTestClass().getRealClass().
					getDeclaredField("webDriver").
					get(result.getInstance());
			System.out.println("Init webdriver");
		} catch(Exception e1) {
			e1.printStackTrace();
			System.out.println("NO webdriver");
		}
		try {
			utils.takeScreenShot(testCaseName, webDriver);
			testLocal.get().addScreenCaptureFromPath(utils.takeScreenShot(testCaseName, webDriver),testCaseName);
			System.out.println("Start capture");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Utils utils = new Utils();
		extentTest.log(Status.FAIL, result.getMethod().getMethodName() + " fail");
		testLocal.get().fail(result.getThrowable());
		
		WebDriver driver = null;
		String testCaseName = result.getMethod().getMethodName();
		 try {
			 driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			 System.out.println("Init webdriver");
		 } catch(Exception e1) {
		 	e1.printStackTrace();
		 	System.out.println("NO webdriver");
		 }
		try {
			utils.takeScreenShot(testCaseName, driver);
			testLocal.get().addScreenCaptureFromPath(utils.takeScreenShot(testCaseName, driver),testCaseName);
			System.out.println("Start capture");
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("NO capture");
		}
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	
	}
	@Override
	public void onStart(ITestContext context) {
        System.out.println("begin execute test case");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("End of test");
		extent.flush();
	}
    
}
