Feature: Login


 Scenario: Login successfully
   Given I access the login page
   And I insert valid credentials
   When I click on login button
   Then I check if user was logged in


  Scenario: Login with wrong credentials
    Given I access the login page
    And I insert invalid credentials
    When I click on login button
    Then I expect invalid credentials message


Scenario: Login with no password
  Given I access the login page
  When I enter "aa@fast.com"/"" credentials
  And I click on login button
  Then I expect "Please enter your password!" error message


Scenario Outline: Failed login
  Given I access the login page
  When I enter "<email>"/"<pass>" credentials
  And I click on login button
  Then I expect "<message>" error message
  Examples:
    | email | pass | message |
    | |  | Please enter your email! |
    | aa@fast.com |  | Please enter your password! |
    |             | onlypass | Please enter you email!|
    | aa@fast.com |  somepass| Please enter your password!|


  Scenario: Logout success
    Given I successfully login





