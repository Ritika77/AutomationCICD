package SeleniumFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import SeleniumFramework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\SeleniumFramework\\resources\\GlobalData.properties");
		prop.load(file);
		//for browser info that is sent from mvn cmds in terminal
		String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browserName= prop.getProperty("browser");
		 if(browserName.contains("chrome"))
		 {
			 ChromeOptions options = new ChromeOptions();
			 WebDriverManager.chromedriver().setup();
			 if(browserName.contains("headless"))
			 {
			   options.addArguments("headless");
			 }
			   driver = new ChromeDriver(options);
			   driver.manage().window().setSize(new Dimension(1440,900));//fullscreen
		}
		 else if(browserName.equalsIgnoreCase("firefox"))
		 {
			 
		 }
		 else if(browserName.equalsIgnoreCase("edge"))
		 {
			// System.setProperty("webdriver.edge.driver", "edge.exe");
			// driver = new EdgeDriver();
		 }
		 
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		 driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		 driver.manage().window().maximize();
		 return driver;
	}
	@BeforeTest 
	public LandingPage launchApplication() throws IOException
	{
		 driver = initializeDriver();
		 landingPage = new LandingPage(driver);
		 landingPage.goTo();
		 return landingPage;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		//typecast driver to on screenshot more
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";	
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.close();
	}
}
