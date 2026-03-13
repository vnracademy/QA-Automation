package vnracademy.pageobject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vnracademy.abstractcomponents.AbstractComponent;

public class MyAccountPage extends AbstractComponent{
	WebDriver driver;
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//div[@id='content']/h2)[1]")
	WebElement myAccountTitle;
	
	public String getMyAccountTitle() {
		return myAccountTitle.getText();
	}
	
}
