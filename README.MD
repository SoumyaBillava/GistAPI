## CRUD operations on GitHub Gists**

Cucumber tests for  CRUD operations on Github [GistAPI](https://developer.github.com/v3/gists)

Tests
You can find 4 feature files each for operation Create, Read, Update and Delete.

###  End points
The end points tested are:

Method | Url          | Description
------ | -----------  | -----------
GET    | /users/{user}/gists | Returns the user's gist, 404 when no such user present.
GET    | /gists | Returns all gists.
GET    | /gists/{gitId} | Returns single gist.404 when no such gist with given id present.
POST   | /gists     | Creates a gist, give as body JSON with the description, public and filename with content, returns a 201 with the created gist.
PATCH    | /gists/{gitId} | Updates an existing gist, give as body JSON with the description, public and filename with content, returns a 200 with the updated gist when a person is present with the specified id.
DELETE | /gists/{gitId} | Deletes the gist with the specified gistId, 404 when no gist is present with this id.

**Prerequisites**
GIST api requires a token for operations like Create, Delete. Please provide the token code in the `config.properties` file

**Running the tests**
The entire suite can be run by executing the `GistTestRunner` class. Individual feature tests can also be executed by going to the feature file.

**Technology used**
 - Cucumber
 - Java
 - Rest Assured
