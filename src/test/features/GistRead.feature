Feature: Read the gists

  Background:
    Given the system is ready

  Scenario: Read all the gists
    When User searches for the gists
    Then the http response code should be 200

  Scenario: Read single gist
    Given gist description is provided as "hello world"
    And gist public access is false
    And gist files provided as:
      | fileName  | fileContent |
      | hello.txt | hello world |
      | bye.txt   | bye world   |
    And User creates a gist
    When User searches for the gist with id
    Then the http response code should be 200
    And the created gist is shown in the result

  Scenario: Read any single gist that doesn't exist
    Given User selects the gist with id f5a0b293ecb0bc744c1ce2d32f0525c
    When User searches for the gist with id
    Then the http response code should be 404

  Scenario: Read all the gists of a user
    When User searches for the gists of a user SoumyaBillava
    Then the http response code should be 200

  Scenario: Read all the gists of a user which does not exist
    When User searches for the gists of a user SoumyaBillav
    Then the http response code should be 404
