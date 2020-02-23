Feature: Delete the gists

  Background:
    Given the system is ready

  Scenario: Delete a gist
    Given User provides gist description "hello world" and isPublic false
    And User provides gist files:
      | fileName  | fileContent |
      | hello.txt | hello world |
      | bye.txt   | bye world   |
    And User creates a gist
    When User deletes the gist
    Then the http response code should be 204

  Scenario: Delete the gist that doesn't exist
    Given User selects the gist with id f5a0b293ecb0bc744c1ce2d32f0525c
    When User deletes the gist
    Then the http response code should be 404
