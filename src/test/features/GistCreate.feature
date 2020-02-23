Feature: Create the gists

  Background:
    Given the system is ready

  Scenario: Create a gist
    Given User provides gist description "hello world" and isPublic false
    And User provides gist files:
      | fileName  | fileContent |
      | hello.txt | hello world |
      | bye.txt   | bye world   |
    When User creates a gist
    Then the http response code should be 201