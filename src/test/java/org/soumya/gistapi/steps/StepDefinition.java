package org.soumya.gistapi.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.soumya.gistapi.client.RestClient;
import org.soumya.gistapi.models.FileDTO;
import org.soumya.gistapi.flatmodels.GistFileDTO;
import org.soumya.gistapi.models.PostGistDTO;

public class StepDefinition {
    private PostGistDTO toBeCreatedGist = new PostGistDTO();
    static RestClient crud = new RestClient();
    String gistId;
    boolean gistCreated = false;

    // check if the system is ready to execute the tests
    @When("the system is ready")
    public void checkSystem() {
        assertNotNull(crud);
        assertNotNull(RestClient.baseUri);
        assertNotNull(RestClient.token);
    }

    @When("^User searches for the gists$")
    public void listGists() {
        crud.listGists();
    }

    @When("^User searches for the gists of a user (.+)$")
    public void listUserGists(String user) {
        System.out.println(user);
        crud.listUsersGists(user);
    }

    @When("^User searches for the gist with id$")
    public void singleGist() {
        crud.listSingleGist(gistId);
    }

    @When("^User selects the gist with id (.*)$")
    public void selectGist(String id) {
        gistId = id;
    }

    @When("^User creates a gist$")
    public void createGist() throws JsonProcessingException {
        gistId = crud.createGist(toBeCreatedGist);

        // if gist creation is successful, mark gistCreated to true so that the gist is
        // deleted at the end of the test
        if (crud.getLastStatusCode() == 201) {
            gistCreated = true;
        }
    }

    @When("^gist description is provided as (.*)$")
    public void createGist(String description) {
        this.toBeCreatedGist.setDescription(description);
    }

    @When("^gist public access is (.*)$")
    public void createGist(Boolean isPublic) {
        this.toBeCreatedGist.setPublic(isPublic);
    }

    @When("^gist files provided as:$")
    public void createGist(List<GistFileDTO> files) {
        HashMap<String, FileDTO> toBeCreatedFiles = new HashMap<>();
        files.forEach(file -> {
            FileDTO fileDTO = new FileDTO(file.getFileContent());
            toBeCreatedFiles.put(file.getFileName(), fileDTO);
        });
        toBeCreatedGist.setFiles(toBeCreatedFiles);
    }

    @When("^User updates the gist$")
    public void updateGist() throws JsonProcessingException {
        crud.editGist(gistId, toBeCreatedGist);
    }

    @When("^User deletes the gist$")
    public void deleteGist() {
        crud.deleteGist(gistId);

        // if gist deletion is successful, mark gistCreated to false so that the gist
        // need not be cleared again at the end of the test
        gistCreated = false;
    }

    @Then("^the http response code should be ([^\"]*)$")
    public void verifyStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, crud.getLastStatusCode());
    }

    @Then("^the created gist is shown in the result$")
    public void verifyGist() {
        Response response = crud.getLastResponse();

        // gets the id of the gist in the response
        String id = response.body().jsonPath().getString("id");
        System.out.println("id is : " + id);

        // compares the id in the result with the created gist to check correct response
        // is returned
        assertEquals(gistId, id);
    }

    @After
    // clearing the gists is required to put the system back to what it was before
    // starting the tests
    public void clearGists() {
        if (gistCreated) {
            crud.deleteGist(gistId);
        }
    }
}
