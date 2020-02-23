package org.soumya.gistapi.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.soumya.gistapi.models.PostGistDTO;
import org.soumya.gistapi.utils.PropertiesLoader;

public class RestClient {
    public static String baseUri = null;
    public static String token = null;
    int lastStatusCode;
    Response lastResponse;

    public RestClient() {
        baseUri = PropertiesLoader.getBaseUrl();
        token = PropertiesLoader.getToken();
    }

    public int getLastStatusCode() {
        return lastStatusCode;
    }

    public void setLastStatusCode(int statusCode) {
        lastStatusCode = statusCode;
    }

    public Response getLastResponse() {
        return lastResponse;
    }

    public void setLastResponse(Response response) {
        lastResponse = response;
    }

    /**
     * lists the gists of the given user
     *
     * @param user
     */
    public void listUsersGists(String user) {
        StringBuilder builder = new StringBuilder();
        builder.append(baseUri);
        builder.append("users/");
        builder.append(user);
        builder.append("/gists");

        System.out.println("uri used : " + builder.toString());
        get(builder.toString());
    }

    /**
     * lists all the gists
     */
    public void listGists() {
        String uri = baseUri + "gists";
        System.out.println("uri used : " + uri);
        get(uri);
    }

    /**
     * Lists the given gist
     *
     * @param gistId
     */
    public void listSingleGist(String gistId) {
        StringBuilder builder = new StringBuilder();
        builder.append(baseUri);
        builder.append("gists/");
        builder.append(gistId);

        System.out.println("uri used : " + builder.toString());
        get(builder.toString());
    }

    /**
     * Does the REST get for the given uri and stores the status code and the
     * response
     *
     * @param uri
     */
    public void get(String uri) {
        RequestSpecification request = RestAssured.given();
        Response response = request.get(uri);
        int statusCode = response.getStatusCode();

        setLastStatusCode(statusCode);
        setLastResponse(response);
    }

    /**
     * Creates a gist with given body
     * @param body
     * @return created gist id
     * @throws JsonProcessingException
     */
    public String createGist(PostGistDTO body) throws JsonProcessingException {
        String uri = baseUri + "gists";
        System.out.println("uri used : " + uri);

        RestAssured.baseURI = uri;
        RequestSpecification request = RestAssured.given();
        request.auth().oauth2(token);
        ObjectMapper objectMapper = new ObjectMapper();
        String postBody = objectMapper.writeValueAsString(body);
        request.body(postBody);

        request.contentType("application/json");
        Response response = request.post();
        int statusCode = response.getStatusCode();

        setLastStatusCode(statusCode);
        setLastResponse(response);

        String id = response.body().jsonPath().getString("id");
        System.out.println("Created gist id is : " + id);

        return id;
    }

    /**
     * Updates the given gist with given body
     *
     * @param id
     * @param body
     */
    public void editGist(String id, PostGistDTO body) throws JsonProcessingException {
        String uri = baseUri + "gists/" + id;
        System.out.println("uri used : " + uri);

        RestAssured.baseURI = uri;
        RequestSpecification request = RestAssured.given();
        request.auth().oauth2(token);
        ObjectMapper objectMapper = new ObjectMapper();
        String postBody = objectMapper.writeValueAsString(body);
        request.body(postBody);

        request.contentType("application/json");
        Response response = request.patch();
        int statusCode = response.getStatusCode();

        setLastStatusCode(statusCode);
        setLastResponse(response);
    }

    /**
     * Deletes the given gist
     *
     * @param id
     */
    public void deleteGist(String id) {
        String uri = baseUri + "gists/" + id;
        System.out.println("uri used : " + uri);

        RestAssured.baseURI = uri;
        RequestSpecification request = RestAssured.given();
        request.auth().oauth2(token);
        request.contentType("application/json");
        Response response = request.delete();
        int statusCode = response.getStatusCode();

        setLastStatusCode(statusCode);
        setLastResponse(response);
    }
}
