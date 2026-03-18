package vnracademy.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import vnracademy.pageobject.LandingPage;
import vnracademy.pageobject.LoginPage;
import vnracademy.pageobject.MyAccountPage;

public class BaseTest {
	
	//  5 multiple threads - each thread should have it's own WebDriver
	// 1 test create its own thread Id -- uni3rq34
	// 2 test creates its own thread Id - un324523
	// 100 tests, Parallel threadcount is 10;
	// first set of 10 test will have their own thread driver object - 10 driver - 1 test excuted remove that driver
	// second set of 10 test will have their own thread driver object - 10 driver
	
	
//	public WebDriver driver;
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public LandingPage landingpage;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/VNRACADEMY/GlobalData/GlobalData.properties");
		prop.load(fis);
		
		String browserName =  System.getProperty("browserName")!=null ? System.getProperty("browserName") : prop.getProperty("browserName");
		System.out.println("browser name : "+ browserName);
		//		String browserName = prop.getProperty("browserName");
		
		switch (browserName.toLowerCase()){
		case "chrome": {
			
			ChromeOptions options = new ChromeOptions();

			Map<String, Object> prefs = new HashMap<>();

			// Disable password manager
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);

			// Disable password breach detection
			prefs.put("profile.password_manager_leak_detection", false);

			// Optional: disable Safe Browsing password protection
			prefs.put("safebrowsing.enabled", false);

			// Always good for automation
//			options.addArguments("--incognito");
			options.setExperimentalOption("prefs", prefs);
//			driver = new ChromeDriver(options);
			driver.set(new ChromeDriver(options));
			break;
		}case "chromeheadless": {
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");

			Map<String, Object> prefs = new HashMap<>();

			// Disable password manager
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);

			// Disable password breach detection
			prefs.put("profile.password_manager_leak_detection", false);

			// Optional: disable Safe Browsing password protection
			prefs.put("safebrowsing.enabled", false);

			// Always good for automation
//			options.addArguments("--incognito");
			options.setExperimentalOption("prefs", prefs);
//			driver = new ChromeDriver(options);
			driver.set(new ChromeDriver(options));
			break;
		}
		case "edge":{
			
			EdgeOptions options = new EdgeOptions();

			Map<String, Object> prefs = new HashMap<>();

			// Disable password manager
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);

			// Disable password breach detection
			prefs.put("profile.password_manager_leak_detection", false);

			// Optional: disable Safe Browsing password protection
			prefs.put("safebrowsing.enabled", false);

			// Always good for automation
//			options.addArguments("--incognito");
			options.setExperimentalOption("prefs", prefs);
//			driver = new EdgeDriver();
			driver.set(new EdgeDriver());
		}
		case "safari":{
			// edge related object will be created
			
			SafariOptions options = new SafariOptions();

			// Example supported capability
//			options.setAutomaticInspection(false);
//			options.setAutomaticProfiling(false);

			
//			driver = new EdgeDriver();
			driver.set(new SafariDriver());
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + browserName.toLowerCase());
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return getDriver();
	}
	
	public String captureScreenShot(String testCaseName) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) getDriver();
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir")+"/reports/"+testCaseName+".png";
		
		File file = new File(path);
		
		Files.copy(source.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		
		return path;
		
	}
	
	
	@BeforeMethod (alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		initializeDriver();
		landingpage = new LandingPage(getDriver());
		landingpage.goTo();
		return landingpage;
	}
	
	@AfterMethod (alwaysRun = true)
	public void tearDown() {
		getDriver().close();
		driver.remove();
	}
	
	public LandingPage loginToApplication(String username, String password) {
		LoginPage loginPage = landingpage.navigateToLoginPage();
		String newCustomerTitle = loginPage.getCustomerTitle();
		Assert.assertEquals(newCustomerTitle, "New Customer", "New Customer page was not Displayed.");
		
		MyAccountPage myAccountPage = loginPage.logintoOpenCart(username, password);
		Assert.assertEquals(myAccountPage.getMyAccountTitle(), "My Account", "My Account page was not Displayed after login.");
		return landingpage;
	}
	
	
	public WebDriver getDriver() {
		return driver.get();
	}
	
	// Execute each test, once completed close the driver object
	
	
}
