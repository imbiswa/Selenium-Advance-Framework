package TestComponenets;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Ekart.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver initializeDriver() throws IOException
	{
		
		Properties prop = new Properties();
		FileInputStream fis =new FileInputStream(System.getProperty("user.dir")+
				"\\src\\main\\java\\resources\\Globaldata.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		
		if(browserName.equalsIgnoreCase(browserName))
		{
			WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
			
		}
		else if(browserName.equalsIgnoreCase(browserName))
		{
			WebDriverManager.firefoxdriver().setup();
		    driver = new FirefoxDriver();
			
		}
		else if(browserName.equalsIgnoreCase(browserName))
		{
			WebDriverManager.edgedriver().setup();
		    driver = new EdgeDriver();
			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
		
	}
	
	@BeforeMethod
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		
		return landingpage;
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}

	
	
}
