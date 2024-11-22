package Ekart.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ekart.AbstractComponents.AbstractComponenet;

public class CartPage extends AbstractComponenet {
	
	 
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail =driver.findElement(By.id("userEmail"));
	//WebElement userPassword = driver.findElement(By.id("userPassword"));
	//driver.findElement(By.id("login")).click();
	
	//implementing page factory design pattern
	
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	
	
	public Boolean VerifyproductDisplay (String productName)
	{
	Boolean match =	cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	return match;	
				
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
		
		
	}

}
