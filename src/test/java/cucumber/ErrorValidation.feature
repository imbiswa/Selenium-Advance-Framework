Feature: Error validation

  @error_validation
  Scenario Outline: validating error message with invalid password
    Given I landed on Ecommerce Page
    When Logged in with username "<name>" and password "<password>"
    Then "Incorrect email or password." message is displayed

   Examples: 
      | name                   | password     |
      | rahulshetty@gmail.com  | Iamking@000  |
      | rahulshetty@gmail.comm | I            | 