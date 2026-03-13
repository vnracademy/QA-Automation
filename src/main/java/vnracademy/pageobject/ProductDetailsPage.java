package vnracademy.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vnracademy.abstractcomponents.AbstractComponent;

public class ProductDetailsPage extends AbstractComponent{
	
WebDriver driver;
	
	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button#grid-view")
	WebElement gridViewButton;
	
	@FindBy(xpath= "//li[contains(text(),'Brand:')]")
	WebElement brandLabel;
	
	@FindBy(id="input-quantity")
	WebElement quantityInput;
	
	@FindBy(id="button-cart")
	WebElement addToCartButton;
	
	@FindBy(css=".alert.alert-success.alert-dismissible")
	WebElement successMessage;
	
	public boolean validateProdcutDetailsPageIsDisplayed() {
		return brandLabel.isDisplayed();
	}
	
	public void addProductToCart(String quantity) {
		quantityInput.sendKeys(quantity);
		addToCartButton.click();
	}
	
	
	public String getSuccessAlertMessage() {
		return successMessage.getText();
	}
	
	

}
