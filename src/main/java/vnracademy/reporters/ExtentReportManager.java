package vnracademy.reporters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	
	public static ExtentReports getExtentReportObj() {
		// Add the Extent Report dependency into our Project (Pom.xml)
		// ExtentReports, ExtentSparkReporter
		// on test pass - log a report with the help of extent object
		// on failure - screenshot along with error details should be displayed in the report.
		// .flush(); - used to generate final extent report with all the logs captured.
		// ExtentReport + TestNG Listener
		
		String reportFilePath = System.getProperty("user.dir")+"/reports/extent-report.html";
		
		ExtentSparkReporter extentSparker = new ExtentSparkReporter(reportFilePath);
		extentSparker.config().setDocumentTitle("Test Automation Results");
		extentSparker.config().setReportName("VNR ACADEMY");
		// Optional (just UI settings)
		extentSparker.config().setTheme(Theme.STANDARD);
		
		// IMPORTANT LINE 👇
		extentSparker.config().setCss("body { font-family: Arial; }"); // optional
		// 🔥 ADD THIS LINE (CRITICAL)
		extentSparker.config().setOfflineMode(true);

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(extentSparker);
		return extent;
		
	}
	
}
