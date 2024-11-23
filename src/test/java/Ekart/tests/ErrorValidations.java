package Ekart.tests;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Ekart.pageobjects.CartPage;
import Ekart.pageobjects.ProductCatalogue;
import TestComponenets.BaseTest;
public class ErrorValidations extends BaseTest{
	
	
	   @Test(groups = {"ErrorHandling"})
	   public void Verify_error_invaliduser_and_password() throws IOException 
			{
			   
			   // landingpage = launchApplication();
			    landingpage.loginApplication("biswam1@gmail.com", "B@123456b1");
			    landingpage.getErrorMessage();
			    Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	
	
		}
	   @Test
	   public void Verify_added_cart_item() throws IOException, InterruptedException
			{
			    String productName = "ZARA COAT 3";

			   // landingpage = launchApplication();
			    ProductCatalogue productCatalogue=landingpage.loginApplication("biswam@gmail.com", "B@123456b");
				
				List<WebElement> products =productCatalogue.getProductList();
				productCatalogue.addProductToCart(productName);
				CartPage cartPage =productCatalogue.goToCartPage();
				
			
	
				Boolean match  = cartPage.VerifyproductDisplay("COAT 3");
				Assert.assertFalse(match);
				
			
	
		}

}

