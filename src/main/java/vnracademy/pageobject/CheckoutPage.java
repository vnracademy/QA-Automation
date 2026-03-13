package vnracademy.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import vnracademy.abstractcomponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//strong[contains(text(),'Checkout')]")
	WebElement checkoutButton;
	
	@FindBy(id="input-payment-firstname")
	WebElement firstNameInput;
	
	@FindBy(id="input-payment-lastname")
	WebElement lastNameInput;
	
	@FindBy(id="input-payment-address-1")
	WebElement AddressInput;
	
	@FindBy(id="input-payment-city")
	WebElement cityInput;
	
	@FindBy(id="input-payment-postcode")
	WebElement postcodeInput;
	
	@FindBy(id="input-payment-country")
	WebElement countryList;
	
	@FindBy(id="input-payment-zone")
	WebElement stateList;
	
	@FindBy(id="button-payment-address")
	WebElement addressButtonContinue;
	
	By countryListEle = By.id("input-payment-country");
	
	
	public CheckoutPage clickOnCartButton() {
		return clickOnCartButton();
	}
	
	public void clickOnCheckOutButton() {
		checkoutButton.click();
	}
	
	public void fillInBillingDetails(String firstName, String lastName, String address1, String city, String postCode, String country, String state) {
		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		AddressInput.sendKeys(address1);
		cityInput.sendKeys(city);
		postcodeInput.sendKeys(postCode);
		selectItemFromStaticList(countryListEle, country);
		selectItemFromStaticList(stateList, state);
		addressButtonContinue.click();
	}
	
	
	
	

}
