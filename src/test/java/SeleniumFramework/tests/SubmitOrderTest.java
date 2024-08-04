package SeleniumFramework.tests;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v125.browser.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFramework.TestComponents.BaseTest;
import SeleniumFramework.data.DataReader;
import SeleniumFramework.pageobjects.CartPage;
import SeleniumFramework.pageobjects.CheckOutPage;
import SeleniumFramework.pageobjects.ConfirmationPage;
import SeleniumFramework.pageobjects.OrderPage;
import SeleniumFramework.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	
	@Test(dataProvider = "getData")
	//public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		String countryName = "india";
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean imatch = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(imatch);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.countrySelection(countryName);
		ConfirmationPage confirmationPage = checkOutPage.orderSubmit();
		String confirmMessage = confirmationPage.captureMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	//@Test(dependsOnMethods = {"submitOrder"})
	//By using depends on the submitOrder is always executed first before OrderHistory as it depends on it.
	public void orderHistoryTest(String email, String password,String productName) throws InterruptedException
	{
		Thread.sleep(2000);
		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	
	//@DataProvider
	//public Object[][] getData()
	//{
	//	return new Object[][] {{"tanu12@gmail.com","tanu@12","ZARA COAT 3"},{"tanu13@gmail.com","Tanuk@13","ADIDAS ORIGINAL"}};
//	}
	
	//@DataProvider
	//public Object[][] getData()
	//{
	//	HashMap<String,String> map = new HashMap<String,String>();
	//	map.put("email", "tanu12@gmail.com");
	//	map.put("password", "tanu@12");
	//	map.put("product", "ZARA COAT 3");
		
	//	HashMap<String,String> map1 = new HashMap<String,String>();
	//	map1.put("email", "tanu13@gmail.com");
	//	map1.put("password", "Tanuk@13");
	//	map1.put("product", "ADIDAS ORIGINAL");
		
	//	return new Object[][] {{map}, {map1}};
	//}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		DataReader dataReader = new DataReader();
		List<HashMap<String, String>> data = dataReader.getJsonDataToMap(System.getProperty("user.dir")+"\\src\\main\\java\\SeleniumFramework\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};	
	}
	
	
	
	
	
}
