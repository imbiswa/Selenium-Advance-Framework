package StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Ekart.pageobjects.CartPage;
import Ekart.pageobjects.CheckoutPage;
import Ekart.pageobjects.ConfirmationPage;
import Ekart.pageobjects.LandingPage;
import Ekart.pageobjects.ProductCatalogue;
import TestComponenets.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("Logged in with username {string} and password {string}")
    public void logged_in_username_and_password(String username, String password) {
        productCatalogue = landingPage.loginApplication(username, password);
    }
    
   
    @When("I add product {string} to Cart")
    public void i_add_product_to_cart(String productName) throws InterruptedException {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }
    
    @When("I click product view {string}")
    public void i_add_product_to_view(String productName) throws InterruptedException {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.clickonviewproductDetails(productName);
    }
    
    

    @When("Checkout {string} and submit the order")
    public void checkout_submit_order(String productName) {
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyproductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("India");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String expectedMessage) {
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(expectedMessage));
        driver.quit();
    }

    @Then("{string} message is displayed")
    public void something_message_is_displayed(String expectedMessage) throws Throwable {
        Assert.assertEquals(expectedMessage, landingPage.getErrorMessage());
        driver.quit();
    }
    @Then("{string} is displayed in profile details page")
    public void is_displayed_in_profile_details_page(String expectedProfile) {
        System.out.println("Expected Profile: " + expectedProfile); // Debugging
        String actualProfile = productCatalogue.getproductProfile();
        System.out.println("Actual Profile: " + actualProfile); // Debugging
        Assert.assertEquals(expectedProfile, actualProfile);
        driver.quit();
    }
    
    
}
