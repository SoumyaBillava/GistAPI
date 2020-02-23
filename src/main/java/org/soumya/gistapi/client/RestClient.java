package org.soumya.gistapi.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.soumya.gistapi.models.PostGistDTO;
import org.soumya.gistapi.utils.PropertiesLoader;

public class RestClient {
    private static String baseUri = null;
    private static String token = null;
    private int lastStatusCode;
    private Response lastResponse;

    public RestClient() {
        baseUri = PropertiesLoader.getBaseUrl();
        token = PropertiesLoader.getToken();
        RestAssured.baseURI = baseUri;
    }

    public static String getBaseUri() {
        return baseUri;
    }

    public static String getToken() {
        return token;
    }

    public int getLastStatusCode() {
        return lastStatusCode;
    }

    public Response getLastResponse() {
        return lastResponse;
    }

    /**
     * lists the gists of the given user
     *
     * @param user : the user for which gists have to be fetched
     */
    public void listUsersGists(String user) {
        lastResponse = createRequestSpecification()
                .get("users/"+user+"/gists");

        lastStatusCode = lastResponse.statusCode();
    }

    /**
     * lists all the gists
     */
    public void listGists() {
        lastResponse = createRequestSpecification()
                .get("/gists");

        lastStatusCode = lastResponse.statusCode();
    }


    /**
     * Creates a gist with given body
     *
     * @param gist : The gist body
     * @throws JsonProcessingException : If body dto cannot be converted to json string
     */
    public String createGist(PostGistDTO gist) throws JsonProcessingException {
        ObjectMapper jacksonObjectMapper = new ObjectMapper();
        String bodyString = jacksonObjectMapper.writeValueAsString(gist);
        lastResponse = createRequestSpecification()
                .body(bodyString)
                .when()
                .post("/gists");
        lastStatusCode = lastResponse.statusCode();
        return lastResponse.body().jsonPath().getString("id");
    }

    /**
     * Get gist by id
     * @param gistId : the id of the gist
     */
    public void getGistById(String gistId){
        lastResponse = createRequestSpecification()
                .get("/gists/"+gistId);

       lastStatusCode = lastResponse.statusCode();
    }

    /**
     * Updates the given gist with given body
     *
     * @param gistDTO : the gist string body
     * @param gistId : the gist id
     */
    public void editGist(PostGistDTO gistDTO, String gistId) throws JsonProcessingException {
        ObjectMapper jacksonObjectMapper = new ObjectMapper();
        String bodyString = jacksonObjectMapper.writeValueAsString(gistDTO);

        lastResponse = createRequestSpecification()
                .body(bodyString)
                .patch("/gists/"+gistId);

        lastStatusCode = lastResponse.statusCode();
    }

    /**
     * Deletes the given gist
     *
     * @param gistId : the id of the gist
     */
    public void deleteGist(String gistId){
        lastResponse = createRequestSpecification()
                .delete("/gists/"+gistId);
        lastStatusCode = lastResponse.getStatusCode();
    }

    private RequestSpecification createRequestSpecification() {
        String contentType = "application/json";
        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(contentType);
    }
}
