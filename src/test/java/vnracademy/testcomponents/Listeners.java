package vnracademy.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import vnracademy.reporters.ExtentReportManager;

// 100 test cases - you might know aleady 20 tests are flaky
// TestNG Feature - Retry mechanism


// 10 driver instances opened means 10 tests opened separately 
// extent loggin alos should have it's own thread associated to open 10 tests
// theard id 

public class Listeners extends BaseTest implements ITestListener{

	ExtentTest test;
	ExtentReports extent = ExtentReportManager.getExtentReportObj();
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
//		if(result.getMethod().getRetryAnalyzer(result) != null) {
//	        if(result.getMethod().getRetryAnalyzer(result).retry(result)) {
//	            return; // skip logging because retry will happen
//	        }
//	    }
		extentTest.get().fail(result.getThrowable());
		
		String filePath = null;
		try {
			filePath = captureScreenShot(result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, "Test Skipped");
	}


	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
}
