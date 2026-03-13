package vnracademy.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vnracademy.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

protected WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Below are the locators for header Section
	
	@FindBy(xpath="//nav[@id='menu']/descendant::ul[1]/li/a")
	protected List<WebElement> menuList;
	
	// Below are the locators for Sub header Section
	
	@FindBy(xpath="//a[text()='Desktop']")
	protected WebElement desktopMenuLink;
	
	@FindBy(css="input[name='search']")
	protected WebElement searchInput;
	
	public void goTo() {
		driver.get("http://localhost/opencart/upload/");
	}
	
	public void mouseHoverOnMenuItem(String menuName) {
		WebElement menuEle = menuList.stream().filter(menuItem -> menuItem.getText().equalsIgnoreCase(menuName)).findFirst()
				.orElseThrow(() -> 
                new RuntimeException("Menu item not found: " + menuName)
            );
		Actions action = new Actions(driver);
		action.moveToElement(menuEle).perform();
	}
	
	public ProductDisplayPage hoverAndClickOnSubMenu(String mainMenu, String subMenuOption) {
		mouseHoverOnMenuItem(mainMenu);
		
		WebElement subMenu = driver.findElement(By.xpath("//a[text()='"+ subMenuOption +"']"));
		subMenu.click();
		return new ProductDisplayPage(driver);
	}
	
	
}
