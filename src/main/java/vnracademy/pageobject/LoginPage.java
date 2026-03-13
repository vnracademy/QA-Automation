package vnracademy.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vnracademy.abstractcomponents.AbstractComponent;

public class LoginPage extends AbstractComponent{
	
	protected WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//div[@id='content']/descendant::h2)[1]")
	WebElement newCustomerTitle;
	
	@FindBy(id="input-email")
	WebElement emailInput;
	
	@FindBy(id="input-password")
	WebElement passwordInput;
	
	@FindBy(css="input.btn.btn-primary")
	WebElement loginButton;
	
	@FindBy(css=".alert.alert-danger.alert-dismissible")
	WebElement loginErrorMessage;
	
	public String getCustomerTitle() {
		String newCustomerTile = newCustomerTitle.getText();
		return newCustomerTile;
	}
	
	public MyAccountPage logintoOpenCart(String email, String password) {
		emailInput.sendKeys(email);
		passwordInput.sendKeys(password);
		loginButton.click();
		return new MyAccountPage(driver);
	}
	
	public String getLoginErrorMessage() {
		return loginErrorMessage.getText();
	}
}
