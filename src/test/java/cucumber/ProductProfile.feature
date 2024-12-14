Feature: Click on product view to verify product profile

  Background: 
    Given I landed on Ecommerce Page

  @profile_Validation
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username "<name>" and password "<password>"
    When I click product view "<productName>"
    Then "<productName>" is displayed in profile details page

    Examples: 
      | name                  | password    | productName        |
      | rahulshetty@gmail.com | Iamking@00  | ZARA COAT 3        |
      | biswam@gmail.com      | B@123456b   | ADIDAS ORIGINAL    |