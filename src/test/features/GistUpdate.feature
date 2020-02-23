Feature: Update the gists

  Background:
    Given the system is ready

  Scenario: Update a gist
    Given User provides gist description "hello world" and isPublic false
    And User provides gist files:
      | fileName  | fileContent |
      | hello.txt | hello world |
      | bye.txt   | bye world   |
    And User creates a gist
    When User provides gist description "not hello world" and isPublic false
    And User updates the gist
    Then the http response code should be 200

  Scenario: Update the gist that doesn't exist
    Given User selects the gist with id f5a0b293ecb0bc744c1ce2d32f0525c
    And User provides gist description "hello world" and isPublic false
    And User provides gist files:
      | fileName  | fileContent |
      | hello.txt | hello world |
      | bye.txt   | bye world   |
    And User updates the gist
    Then the http response code should be 404
