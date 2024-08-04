package SeleniumFrameworkDesign.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFramework.TestComponents.BaseTest;
import SeleniumFramework.pageobjects.CartPage;
import SeleniumFramework.pageobjects.CheckOutPage;
import SeleniumFramework.pageobjects.ConfirmationPage;
import SeleniumFramework.pageobjects.LandingPage;
import SeleniumFramework.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDedinitionImpl  extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue; 
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	 
	@Given ("^Logged in with username(.+) and password(.+)$")
	public void Logged_in_with_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username,password);
	}
	
	@When("^I add product(.+) to the cart$")
	public void I_add_product_to_the_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout(.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean imatch = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(imatch);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.countrySelection("india");
		confirmationPage = checkOutPage.orderSubmit();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string)
	{
		String confirmMessage = confirmationPage.captureMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	
	
	

}
 