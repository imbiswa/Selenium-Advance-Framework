package Ekart.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Ekart.AbstractComponents.AbstractComponenet;

public class ProductCatalogue extends AbstractComponenet{
	
	 
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver); //giving life about driver from child to parent so super keyword used
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	 List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	 WebElement spinner;
	
	@FindBy(css="div h2")
	WebElement profileofProduct;
	
	By productsBy=By.cssSelector(".mb-3");
	By addToCart =By.cssSelector(".card-body button:last-of-type");
	By clicktoView=By.cssSelector("i[class='fa fa-eye']");
	By toastMessage = By.cssSelector("#toast-container");
	By productProfile =By.cssSelector("div h2");
	
	public List<WebElement> getProductList()
	
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod= getProductList().stream().filter(product->product.findElement(By.cssSelector("b"))
				.getText().equals(productName)).findFirst().orElse(null);
		
		
		return prod;
	}
	
	
	
	public void addProductToCart(String ProductName) throws InterruptedException 
	{
		WebElement prod = getProductByName(ProductName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementTODisappear(spinner);
		
	}
	
	public void clickonviewproductDetails(String ProductName) throws InterruptedException 
	{
		WebElement prod = getProductByName(ProductName);
		prod.findElement(clicktoView).click();
			
	}
	public String getproductProfile()
	{
		String profile =profileofProduct.getText();
		return profile;
		
	}
	
	
	
	
	 	

}
