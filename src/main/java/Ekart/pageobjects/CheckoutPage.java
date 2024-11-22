package Ekart.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Ekart.AbstractComponents.AbstractComponenet;

public class CheckoutPage extends AbstractComponenet {
	
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory .initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy (css ="[placeholder ='Select Country']")
	WebElement country;
	
	@FindBy (xpath ="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement submit;
	
	@FindBy (xpath = "//button[contains(@class, 'ta-item')][2]")
	WebElement SelectCountry;
	
	By results =By.cssSelector("ta-results");

	public void selectCountry(String countryName)
	{
		Actions a = new Actions (driver);
		a.sendKeys(country, countryName).build().perform();
		SelectCountry.click();
		
		
	}
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}
	
	
	

}
