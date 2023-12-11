package wikipedia;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports {
     static ExtentReports extentReport;
	
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir")+"/src/reports/index.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setDocumentTitle("Tam Anh Hospital");
		report.config().setReportName("Validate E2E function ");
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(report);
		extentReport.setSystemInfo("QC/QA", "Dung Doan");
		//extentReport.setSystemInfo("Platfom",util.getValue("browser") );
		extentReport.setSystemInfo("OS", "Mac");
		
		return extentReport;
	}
}
