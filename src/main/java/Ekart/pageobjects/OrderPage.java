package Ekart.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ekart.AbstractComponents.AbstractComponenet;

public class OrderPage extends AbstractComponenet {
	
	 
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver)
	
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(xpath="//tbody//tr//td[2]")
	private List<WebElement> orderedproducts;
	
	
	
	public Boolean VerifyOrdertDisplay (String productName)
	{
	Boolean match =	orderedproducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	return match;	
				
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
		
		
	}

}
