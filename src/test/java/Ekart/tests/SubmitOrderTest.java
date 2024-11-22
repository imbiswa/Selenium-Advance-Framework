package Ekart.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Ekart.pageobjects.CartPage;
import Ekart.pageobjects.CheckoutPage;
import Ekart.pageobjects.ConfirmationPage;
import Ekart.pageobjects.LandingPage;
import Ekart.pageobjects.OrderPage;
import Ekart.pageobjects.ProductCatalogue;
import TestComponenets.BaseTest;
public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
	
	   @Test
	   public void submitOrder() throws IOException, InterruptedException
			{
			    
			   
			   landingpage = launchApplication();
			    ProductCatalogue productCatalogue=landingpage.loginApplication("biswam@gmail.com", "B@123456b");
				
				List<WebElement> products =productCatalogue.getProductList();
				productCatalogue.addProductToCart(productName);
				CartPage cartPage =productCatalogue.goToCartPage();
				
			
	
				Boolean match  = cartPage.VerifyproductDisplay(productName);
				Assert.assertTrue(match);
				CheckoutPage checkoutPage =cartPage.goToCheckout();
				checkoutPage.selectCountry("India");
				ConfirmationPage confirmationPage =checkoutPage.submitOrder();
				String confirmMessage  = confirmationPage.getConfirmationMessage();
				Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
				System.out.println(confirmMessage);
				
		}
	   @Test(dependsOnMethods = {"submitOrder"})
	   public void orderHistoryTest()
	   {
		   ProductCatalogue productCatalogue=landingpage.loginApplication("biswam@gmail.com", "B@123456b");
		   OrderPage orderPage= productCatalogue.goToOrderPage();
		   Assert.assertTrue(orderPage.VerifyOrdertDisplay(productName));
	   }

	
		
		
}

