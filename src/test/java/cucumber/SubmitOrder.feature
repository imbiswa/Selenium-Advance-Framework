Feature: Purchase the order from Ecommerce Website

  Background: 
    Given I landed on Ecommerce Page

  @E2E
  Scenario Outline: Positive Test of profile deatils
    Given Logged in with username "<name>" and password "<password>"
    When I add product "<productName>" to Cart
    And Checkout "<productName>" and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
    
    Examples: 
      | name                  | password    | productName    |
      | rahulshetty@gmail.com | Iamking@00  | ZARA COAT 3    |
      | biswam@gmail.com      | B@123456b   | ADIDAS ORIGINAL|
    