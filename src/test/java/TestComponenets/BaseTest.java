package TestComponenets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	
	
	public List<HashMap<String, String>> getJsondataTomap(String filepath) throws IOException
	{
		//read json to string
		String jsonContent =FileUtils.readFileToString(new File(filepath),
				StandardCharsets.UTF_8);
		
		//String to Hasmap jackson datbind
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap <String , String>> data = mapper.readValue
				(jsonContent, new TypeReference <List <HashMap <String , String>>>(){
				
				});
		
		return data;
	}
	
	
	public String getScreenshot(String testcasename, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		 File Source =ts.getScreenshotAs(OutputType.FILE);
		 
		 File file = new File (System.getProperty(("user.dir")+"//reports//"+testcasename+".png"));
		 
		 FileUtils.copyFile(Source, file);
		 
		 return System.getProperty("user.dir")+"//reports//"+testcasename+".png";
	}
	
	
	@BeforeTest(alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		
		return landingpage;
	}
	//always run true because , for testng is is being treated as a test , if we group test cases then it will not run and we can't make
	// this two as apart of any group 
	
	@AfterTest(alwaysRun = true)
	public void tearDown()
	{
		driver.close();
	}

	
	
}
