#Author: your.email@your.domain.com
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Submit Order from ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page

  @tag1
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <username> and password <password>
    When I add product <productName> to the cart
    And Checkout <productname> and submit the order
    Then "THANKYOU FOR YOUR ORDER." message is displayed on ConfirmationPage

    Examples: 
      |username  			 |password|productName |
      |tanu12@gmail.com|tanu@12 |ZARA COAT 3 |
      
