package SeleniumFramework.tests;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFramework.TestComponents.BaseTest;

public class ErrorValidations extends BaseTest{
	@Test
	public void submitOrder() throws IOException, InterruptedException
	{
		String email = "tanu12@gmail.com";
		String password = "tan@12";
		landingPage.loginApplication(email, password);
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
			
	}

}
