package Ekart.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Ekart.pageobjects.LandingPage;
import Ekart.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		LandingPage landingpage = new LandingPage(driver);
		//driver.get("https://rahulshettyacademy.com/client");
		
		
		
		landingpage.goTo();
		landingpage.loginApplication("biswam@gmail.com", "B@123456b");
		
		
		//driver.findElement(By.id("userEmail")).sendKeys("biswam@gmail.com");
		//driver.findElement(By.id("userPassword")).sendKeys("B@123456b");
		//driver.findElement(By.id("login")).click();
		
		
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		  //  List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	
		
		//stream is working as a for loop and then filtering(condition)
		
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement >products=productCatalogue.getProductList();
		
		
		
		
		//WebElement prod= products.stream().filter(product->product.findElement(By.cssSelector("b"))
			//	.getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		
		
		//orElse if returns nothing then return null//findfirst finds the first element
		
		
		//System.out.println(prod);
		// prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		productCatalogue.addProductToCart(productName);
		
		
		
		
		// WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		// wait.until(ExpectedConditions.invis	ibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();		

		productCatalogue.goToCartPage();
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
	
		
	Boolean match =	cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	
	Assert.assertTrue(match);
	
	driver.findElement(By.cssSelector(".totalRow button")).click();
	
	Actions a = new Actions (driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	//here in sendkeys we have two arguments , find element and value
	
	
	driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	
	driver.findElement(By.cssSelector(".action__submit")).click();
	
	String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	driver.close();	
	
}
	}

