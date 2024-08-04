package SeleniumFramework.pageobjects;
import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumFrameworks.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	WebDriver driver;
	By countryDropDown = By.cssSelector(".ta-results");
	
//create a constructor to call driver 
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	//PageFactory
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	@FindBy(css=".action__submit")
	WebElement submit;
	
	public void countrySelection(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		waitForElementToAppear(countryDropDown);
		selectCountry.click();
	}
	
	public ConfirmationPage orderSubmit()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}
	

}
