package vnracademy.opencart;

import org.testng.Assert;
import org.testng.annotations.Test;


import vnracademy.pageobject.LoginPage;
import vnracademy.testcomponents.BaseTest;
import vnracademy.testcomponents.Retry;

public class ErrorValidationTest extends BaseTest{

	
	@Test
	public void loginToApplicationWithInvalidCredentials() {
		
		String expErrorMsg = "Warning: No match for E-Mail Address and/or Password.";
		LoginPage loginPage = landingpage.navigateToLoginPage();
		loginPage.logintoOpenCart("dsgfa@gmail.com", "test");
		String loginFailedErrorMessage = loginPage.getLoginErrorMessage();
		Assert.assertTrue(loginFailedErrorMessage.contains(expErrorMsg), "Login Was successfull with invalid credentials. But expected message: "+expErrorMsg);
	}
	
}
