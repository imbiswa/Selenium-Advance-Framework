package Ekart.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Ekart.pageobjects.CartPage;
import Ekart.pageobjects.CheckoutPage;
import Ekart.pageobjects.ConfirmationPage;
import Ekart.pageobjects.OrderPage;
import Ekart.pageobjects.ProductCatalogue;
import TestComponenets.BaseTest;
import TestComponenets.Retry;
public class SubmitOrderTest extends BaseTest{

	String prdct= "ZARA COAT 3";
	
	   @Test(dataProvider = "getData", groups= {"Purchase"}, retryAnalyzer=Retry.class)
	   public void submitOrder( HashMap <String,String> input) throws IOException, InterruptedException
			{
			    
			   
			 
			    ProductCatalogue productCatalogue=landingpage.loginApplication(input.get("email"), input.get("password"));
				List<WebElement> products =productCatalogue.getProductList();
				productCatalogue.addProductToCart(input.get("product"));
				CartPage cartPage =productCatalogue.goToCartPage();
				Boolean match  = cartPage.VerifyproductDisplay(input.get("product"));
				String prdct = input.get("product");
				System.out.println(input.get("product"));
				Assert.assertTrue(match);
				CheckoutPage checkoutPage =cartPage.goToCheckout();
				checkoutPage.selectCountry("India");
				ConfirmationPage confirmationPage =checkoutPage.submitOrder();
				String confirmMessage  = confirmationPage.getConfirmationMessage();
				Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
				System.out.println(confirmMessage);
			
		}
	   @Test(dependsOnMethods = {"submitOrder"})
	   public void orderHistoryTest(String prdct )
	   {
		   
		   ProductCatalogue productCatalogue=landingpage.loginApplication("biswam@gmail.com", "B@123456b");
		   OrderPage orderPage= productCatalogue.goToOrderPage();
		   Assert.assertTrue(orderPage.VerifyOrdertDisplay(prdct));
	   }
	   
	   @DataProvider
	   public Object[][] getData() throws IOException
	   {

		  List<HashMap<String, String>> data = getJsondataTomap((System.getProperty("user.dir")
					+"//src//test//java//data//Purchase.json"));
		   return new Object [][] {{data.get(0)},{data.get(1)}};
		   
		 
	
	   }

//	   HashMap <String,String > map = new HashMap <String,String>();
//	   map.put("email", "biswam@gmail.com");
//	   map.put("password", "B@123456b");
//	   map.put("product", "ZARA COAT 3");
//	   
//	   HashMap <String,String > map1 = new HashMap <String,String>();
//	   map1.put("email", "shetty@gmail.com");
//	   map1.put("password", "Iamking@000");
//	   map1.put("product", "ADIDAS ORIGINAL");
		
		
}

