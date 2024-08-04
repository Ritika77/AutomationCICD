package SeleniumFramework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworks.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	WebDriver driver;
	
//create a constructor to call driver 
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	//PageFactory
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	
	public String captureMessage()
	{
		return confirmMessage.getText();
		 
	}
	
	 
	
	


}
