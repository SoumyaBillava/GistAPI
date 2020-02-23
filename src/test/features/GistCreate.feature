Feature: Create the gists

  Background:
    Given the system is ready

  Scenario: Create a gist
    Given gist description is provided as "hello world"
    And gist public access is false
    And gist files provided as:
      | fileName  | fileContent |
      | hello.txt | hello world |
      | bye.txt   | bye world   |
    When User creates a gist
    Then the http response code should be 201
    And the created gist is shown in the result