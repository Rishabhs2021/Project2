Feature: GET and POST calls

  Scenario: Check GET call to the People API
    Given a People API Base URI
    When make a GET call
    Then validate the person name
    
    
	Scenario: Check POST call to the Users API
  	Given a Users API Base URI
    Then make a POST call

