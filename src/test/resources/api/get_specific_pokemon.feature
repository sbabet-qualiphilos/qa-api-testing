Feature: Get specific pokemon details

  Scenario: Retrieve a list of all Pokemon
    Given I look for a "flying" type pokemon named "charizard"
    Then The search should return pokemon info
    And Status Code should return 200
    And The pokemon type is "fire"
