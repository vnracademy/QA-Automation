package vnracademy.opencart;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vnracademy.pageobject.CheckoutPage;
import vnracademy.pageobject.LandingPage;
import vnracademy.pageobject.ProductDetailsPage;
import vnracademy.pageobject.ProductDisplayPage;
import vnracademy.testcomponents.BaseTest;
import vnracademy.testcomponents.Retry;
import vnracademy.utils.JsonReader;

public class AddProductsToCartTest extends BaseTest{
//	WebDriver driver;
	
	@Test (groups = {"products"}, dataProvider = "getData")
	public void addProductsToCart(HashMap<String, String> inputData) throws InterruptedException, IOException {
		
		String email = inputData.get("email");
		String password = inputData.get("password");
		String productName =  inputData.get("product") ;
		String expSuccessMsg = "Success: You have added "+productName+" to your shopping cart!";
//		String expQuantity = "2";
		String quantity =  inputData.get("quantity") ;
		
		// Login to application
		LandingPage landingpage =  loginToApplication(email, password);
		
		// Navigating to PDP page from Desktop menu
		ProductDisplayPage pdpPage = landingpage.hoverAndClickOnSubMenu("Desktops", "Show All Desktops");
		Assert.assertTrue(pdpPage.validatePDPPageIsDisplayed());
		
		// product display page actions
		ProductDetailsPage productDetailsPage = pdpPage.selectProduct(productName);
		Assert.assertTrue(productDetailsPage.validateProdcutDetailsPageIsDisplayed());
		productDetailsPage.addProductToCart(quantity);
		String successMessage = productDetailsPage.getSuccessAlertMessage();
		Assert.assertTrue(successMessage.contains(expSuccessMsg), "Expected Message: "+expSuccessMsg+" does not contain in Actual Alert Message "+successMessage);
		
	}
	
	@Test(dependsOnMethods = {"addProductsToCart"}, retryAnalyzer = Retry.class)
	public void checkoutTest() {
		
		LandingPage landingpage =  loginToApplication("Test1@gmail.com", "Test@123");
		CheckoutPage checkoutPage = landingpage.clickOnCartButton();
		checkoutPage.clickOnCheckOutButton();
		checkoutPage.fillInBillingDetails("vnr", "academy", "dmm", "atp", "515672", "India", "Andhra Pradesh");
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
//		Object[][] data = new Object[100][100];
//		data[0][0] = "Test1@gmail.com"; 
//		{{"Test1@gmail.com", "Test@123"},{"Test1@gmail.com", "Test@123"}};
//		return data;
		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "Test1@gmail.com");
//		map.put("password", "Test@123");
//		map.put("product", "iPhone");
//		map.put("quantity", "4");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "venkat_test@gmail.com");
//		map1.put("password", "Test@123");
//		map1.put("product", "MacBook");
//		map1.put("quantity", "5");
		
		
		JsonReader jsReader =  new JsonReader();
		List<HashMap<String, String>> data = jsReader.getJsonDataToMap("src/test/java/VNRACADEMY/data/PurchageOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	// Step 1 - create Json file with different data sets (Combination of data)
	
	// Step 2 - Load the json file and 

	// Step 3 - TestNG Data provider expects data in multi Dimentional array
	
	// Step 4 - read each data set from multi Dimentional array and pass the data set to actual test case.
	
	
	
	// Entent Report -- HTML -- on failure will capture screenshots and attach to the report for each test
	
	
	
}
