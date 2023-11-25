Feature: Acceptance test for ISTQB Project
   Description: The purpose of these tests is to validate end to end workflows of the application.

  Scenario: User is able to get a list of users
    When I query the API for a list of users
    Then Then a list of users is returned
