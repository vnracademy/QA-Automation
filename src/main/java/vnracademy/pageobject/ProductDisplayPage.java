package vnracademy.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vnracademy.abstractcomponents.AbstractComponent;

public class ProductDisplayPage extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductDisplayPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button#grid-view")
	WebElement gridViewButton;
	
	@FindBy(xpath="//div[@class='product-thumb']/descendant::div[@class='caption']/descendant::a")
	List<WebElement> productNames;
	
	public boolean validatePDPPageIsDisplayed() {
		return gridViewButton.isDisplayed();
	}
	
	public ProductDetailsPage selectProduct(String prodcutName) {
		WebElement productEle = productNames.stream().filter(product -> product.getText().contains(prodcutName)).findFirst()
		.orElseThrow(() -> 
        new RuntimeException("Product was not found: " + prodcutName));
        
        productEle.click();
        
        return new ProductDetailsPage(driver);
	}
	
	
}
