package vnracademy.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import vnracademy.pageobject.CheckoutPage;
import vnracademy.pageobject.LoginPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement myAccount;
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement loginButton;
	
	@FindBy(xpath="//a[text()='Register']")
	WebElement registerButtin;
	
	@FindBy(css=".btn-group.btn-block")
	WebElement cartButton;
	
	public CheckoutPage clickOnCartButton() {
		cartButton.click();
		return new CheckoutPage(driver);
	}
	
	public void clickOnRegisterButton() {
		registerButtin.click();
	}
	
	public void clickOnMyAccount() {
		myAccount.click();
	}
	
	public LoginPage clickLogin() {
		loginButton.click();
		return new LoginPage(driver);
	}
	
	
	public LoginPage navigateToLoginPage() {
		clickOnMyAccount();
		clickLogin();
		return new LoginPage(driver);
	}
	
	public LoginPage navigateToRegisterPage() {
		clickOnMyAccount();
		clickOnRegisterButton();
		return new LoginPage(driver);
	}
	
	public void waitForElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToAppearByLocator(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDisAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(findBy));
	}
	
	public void waitForElementToDisAppearByLocator(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void selectItemFromStaticList(By findBy, String valueToSelect) {
		WebElement listBox = driver.findElement(findBy);
		Select select = new Select(listBox);
//		select.selectByValue(valueToSelect);
		select.selectByVisibleText(valueToSelect);
		
	}
	
	public void selectItemFromStaticList(WebElement findBy, String valueToSelect) {
		Select select = new Select(findBy);
//		select.selectByValue(valueToSelect);
		select.selectByVisibleText(valueToSelect);
		
	}
	
	
}
