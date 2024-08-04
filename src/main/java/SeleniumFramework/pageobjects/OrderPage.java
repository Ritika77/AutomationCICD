package SeleniumFramework.pageobjects;
import java.util.List;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworks.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	WebDriver driver;
	
//create a constructor to call driver 
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	//PageFactory
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	 
	public Boolean verifyOrderDisplay(String productName)
	{
		Boolean imatch = productNames.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));
		return imatch;
	}

}
