package SeleniumFramework.pageobjects;
import java.util.List;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworks.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	
//create a constructor to call driver 
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	//PageFactory
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	 
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean imatch = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return imatch;
	}
	
	public CheckOutPage goToCheckOut()
	{
		checkOut.click();
		return new CheckOutPage(driver);
	}
	

	


}
