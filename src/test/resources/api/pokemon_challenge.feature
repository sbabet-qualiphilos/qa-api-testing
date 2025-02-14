Feature: Pokemon challenge

  @sanity
  Scenario: Validate ability details of a random Pokemon
    Given I request a list of all Pokemon
    And I request details for the stored Pokemon
    And I request details for the stored Pokemon ability
    Then The response should contain the stored ability name
    And The ability should include the stored Pokemon name
    And Status Code should return 200