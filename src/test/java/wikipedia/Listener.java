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

public class Listener extends Base implements ITestListener {
    ExtentReports extent = Reports.getReportObject();
	ExtentTest extentTest;
	ThreadLocal<ExtentTest> testLocal = new ThreadLocal<ExtentTest>();//give pool contain variable "extentTest" to define what test is running
	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extent.createTest(result.getMethod().getMethodName());
		testLocal.set(extentTest);//set the present running test to the pool
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		testLocal.get().log(Status.PASS, result.getMethod().getMethodName() + " pass");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
        utils = new Utils();
		extentTest.log(Status.FAIL, result.getMethod().getMethodName() + " fail");
		testLocal.get().fail(result.getThrowable());
		
		WebDriver webDriver = null;
		String testCaseName = result.getMethod().getMethodName();
			try {
				webDriver =	(WebDriver) result.getTestClass().getRealClass().getDeclaredField("webDriver")
						.get(result.getInstance());
			} catch(Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		try {
			
			testLocal.get().addScreenCaptureFromPath(utils.takeScreenShot(testCaseName, webDriver),testCaseName);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		extent.flush();
	}
    
}
