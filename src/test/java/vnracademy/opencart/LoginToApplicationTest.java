package vnracademy.opencart;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import vnracademy.testcomponents.BaseTest;

public class LoginToApplicationTest extends BaseTest{
	
	
//	@Test
	public void register() {
		
		// Browsers (Chrome, FireFox, Edge, Opera..)
		// For each browser there will be one driver class defined in selenum
		// chrome - chromeDriver()
		// firefox - FirefoxDriver()
		// Edge - EdgeDriver()
		
//		 WebDriver driver = new ChromeDriver();
//		 driver.get("http://localhost/opencart/upload/");
//		 driver.manage().window().maximize();
//		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		 
////		 HeaderSection headerSection = new HeaderSection(driver);
//			
//		 headerSection.clickOnMyAccount();
//		 headerSection.clickLogin();
//		 String actRegisterTitle = driver.findElement(By.cssSelector("#content h1")).getText();
//		 AssertJUnit.assertEquals(actRegisterTitle, "Register Account", "Register Account Section was not Displayed.");
//		 driver.findElement(By.id("input-firstname")).sendKeys("VNR");
//		 driver.findElement(By.id("input-lastname")).sendKeys("ACADEMY");
//		 driver.findElement(By.cssSelector("input[placeholder='E-Mail']")).sendKeys("Test1@gmail.com");
//		 driver.findElement(By.id("input-telephone")).sendKeys("9532452331");
//		 driver.findElement(By.id("input-password")).sendKeys("Test@123");
//		 driver.findElement(By.id("input-confirm")).sendKeys("Test@123");
//		 driver.findElement(By.cssSelector("input[name='agree']")).click();
//		 driver.findElement(By.cssSelector(".btn.btn-primary")).click();
//		 String actAccountCreationMessage = driver.findElement(By.cssSelector("#content h1")).getText();
//		 AssertJUnit.assertEquals(actAccountCreationMessage, "Your Account Has Been Created", "Your Account Has Been Created! message was not Displayed."); 
		
	}
	
	@Test
	public void loginToOpenCart() {
		
		loginToApplication("Test1@gmail.com", "Test@123");
		
	}
	
}
